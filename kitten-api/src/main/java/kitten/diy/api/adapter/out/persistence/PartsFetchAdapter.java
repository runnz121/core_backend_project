package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruPartsTag;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruPartsTagRepository;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.entity.Theme;
import kitten.core.coredomain.theme.entity.ThemeParts;
import kitten.core.coredomain.theme.repository.ThemePartsRepository;
import kitten.core.coredomain.theme.repository.ThemeRepository;
import kitten.diy.api.application.port.in.command.command.PartsSearchCommand;
import kitten.diy.api.application.port.in.query.data.PartsThemeData;
import kitten.diy.api.application.port.out.PartsFetchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class PartsFetchAdapter implements PartsFetchPort {

    private final MoruPartsRepository moruPartsRepository;
    private final MoruPartsTagRepository moruPartsTagRepository;
    private final ThemeRepository themeRepository;
    private final ThemePartsRepository themePartsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PartsThemeData> getPartsByTheme(PartsSearchCommand command) {
        Theme theme = themeRepository.findByType(command.themeType()).orElse(null);
//        if (theme.isPresent() == false) return new ArrayList<>();
        return getPartsThemeDatas(theme);
    }

    private List<PartsThemeData> getPartsThemeDatas(Theme theme) {
        List<ThemeParts> themeParts = new ArrayList<>();
        // null 이면 전체 파츠
        if (Objects.isNull(theme)) {
            themeParts = (List<ThemeParts>) themePartsRepository.findAll();
        } else {
            themeParts = themePartsRepository.findAllByTheme(theme);
        }
        Map<ThemePosition, List<ThemeParts>> positionMap = themeParts.stream().collect(groupingBy(ThemeParts::getPosition));
        return positionMap.entrySet().stream()
                .map(data -> {
                    List<PartsThemeData.PartsData> parentDatas = getParentDatas(data);
                    return PartsThemeData.createPartsThemeData(theme, data.getKey(), parentDatas);
                })
                .toList();
    }

    private List<PartsThemeData.PartsData> getParentDatas(Map.Entry<ThemePosition, List<ThemeParts>> data) {
        return data.getValue().stream()
                .map(this::createPartsData)
                .filter(Objects::nonNull)
                .toList();
    }

    private PartsThemeData.PartsData createPartsData(ThemeParts parent) {
        return moruPartsRepository.findByKey(parent.getParts().getKey())
                .map(parentParts -> {
                    List<String> partTags = getTags(parentParts.getKey());
                    List<PartsThemeData.PartsData> childDatas = getPartsData(parent);
                    return PartsThemeData.PartsData.createMoruParts(parentParts, childDatas, partTags, false);
                })
                .orElse(null);
    }

    private List<PartsThemeData.PartsData> getPartsData(ThemeParts partParent) {
        Long parentPartKey = partParent.getParts().getKey();
        List<MoruParts> childMoruParts = moruPartsRepository.findAllByParentKey(parentPartKey);
        // 대표 이미지만 존재할 경우 child data는 보내지 않음
        if (CollectionUtils.isEmpty(childMoruParts)) {
            return Collections.emptyList();
        }
        MoruParts parentMoruParts = moruPartsRepository.findByKey(parentPartKey).get();
        List<PartsThemeData.PartsData> childData = childMoruParts.stream()
                .map(childMoruPart -> PartsThemeData.PartsData.createMoruParts(childMoruPart,null,null, true))
                .collect(Collectors.toList());
        childData.add(PartsThemeData.PartsData.createMoruParts(parentMoruParts,null,null, true));
        childData.sort(Comparator.comparing(PartsThemeData.PartsData::partsKey));
        return childData;
    }

    private List<String> getTags(Long moruPartParentKey) {
        return moruPartsTagRepository.findAllByMoruParts_Key(moruPartParentKey).stream()
                .map(MoruPartsTag::getTag)
                .toList();
    }
}
