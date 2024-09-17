package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruPartsTag;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruPartsTagRepository;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.entity.Theme;
import kitten.core.coredomain.theme.entity.ThemeParts;
import kitten.core.coredomain.theme.repository.ThemePartsRepository;
import kitten.core.coredomain.theme.repository.ThemeRepository;
import kitten.diy.api.adapter.out.error.ThemeError;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import kitten.diy.api.application.port.out.ItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class ItemFetchAdapter implements ItemPort {

    private final MoruPartsRepository moruPartsRepository;
    private final MoruPartsTagRepository moruPartsTagRepository;
    private final ThemeRepository themeRepository;
    private final ThemePartsRepository themePartsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PartsThemeData> getPartsByTheme(PartsSearchCommand command) {
        Optional<Theme> theme = themeRepository.findByType(command.themeType());
        if (theme.isPresent() == false) return new ArrayList<>();
        return getPartsThemeDatas(command, theme.get());
    }

    private List<PartsThemeData> getPartsThemeDatas(PartsSearchCommand command,
                                                    Theme theme) {
        List<ThemeParts> themeParts = themePartsRepository.findAllByTheme(theme);

        Map<ThemePosition, List<ThemeParts>> positionMap = themeParts.stream().collect(groupingBy(ThemeParts::getPosition));

        return positionMap.entrySet().stream()

                .map(data -> {

                    List<PartsThemeData.PartsData> parentData = data.getValue().stream().map(
                            parent -> {
                                Optional<MoruParts> parentParts = moruPartsRepository.findByParentKey(parent.getParts().getKey());
                                if (parentParts.isPresent() == false) return null;
                                List<String> partTags = getTags(parentParts.get());
                                List<PartsThemeData.PartsData> childDatas = getPartsData(parent);
                                return PartsThemeData.PartsData.createMoruParts(parentParts.get(), childDatas, partTags, false);
                            }
                    ).toList();

                    return PartsThemeData.builder()
                            .themeType(theme.getType())
                            .themePosition(data.getKey())
                            .parentData(parentData)
                            .build();

                })
                .toList();
    }

    private List<PartsThemeData.PartsData> getPartsData(ThemeParts partParent) {
        Long parentPartKey = partParent.getParts().getKey();
        List<MoruParts> childMoruParts = moruPartsRepository.findAllByParentKey(parentPartKey);
        return childMoruParts.stream()
                .map(childMoruPart -> PartsThemeData.PartsData.createMoruParts(childMoruPart,null,null, true))
                .toList();
    }

    private List<String> getTags(MoruParts moruParts) {
        return moruPartsTagRepository.findAllByMoruParts(moruParts).stream()
                .map(MoruPartsTag::getTag)
                .toList();
    }
}
