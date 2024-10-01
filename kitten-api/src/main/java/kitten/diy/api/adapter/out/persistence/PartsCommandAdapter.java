package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.annotation.Description;
import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.moru.consts.MoruStatus;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruPartsTag;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruPartsTagRepository;
import kitten.core.coredomain.parts.entity.Parts;
import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.core.coredomain.theme.entity.Theme;
import kitten.core.coredomain.theme.entity.ThemeParts;
import kitten.core.coredomain.theme.repository.ThemePartsRepository;
import kitten.core.coredomain.theme.repository.ThemeRepository;
import kitten.diy.api.adapter.out.error.PartsErrorCode;
import kitten.diy.api.adapter.out.error.ThemeError;
import kitten.diy.api.application.port.in.command.command.PartsCommand;
import kitten.diy.api.application.port.out.PartsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartsCommandAdapter implements PartsPort {

    private final MoruPartsRepository moruPartsRepository;
    private final MoruPartsTagRepository moruPartsTagRepository;

    private final ThemeRepository themeRepository;
    private final ThemePartsRepository themePartsRepository;

    @Override
    @Transactional
    public void saveMoruParts(PartsCommand command) {

        PartsCommand.PartsChild parentData = command.childData().stream()
                .filter(PartsCommand.PartsChild::isRepresentative)
                .findFirst()
                .orElseThrow(() -> new CommonRuntimeException(PartsErrorCode.NOT_REPRESENTATIVE_PARTS_EXISTS));

        MoruParts parentMoruParts = MoruParts.builder()
                .parentKey(null)
                .representative(true)
                .status(MoruStatus.PENDING)
                .name(command.partsName())
                .width(command.width())
                .height(command.height())
                .purchaseInfo(command.getPurchaseInfos())
                .imageUrl(parentData.partImageUrl())
                .colorHexCode(parentData.colorhexCode())
                .build();

        MoruParts parentSave = moruPartsRepository.save(parentMoruParts);

        // theme parts 저장
        ThemeType type = command.theme();
        Theme themeByType = getThemeByType(type);

        // TOOD 파츠 저장시 부위 선택 필요 확인
        ThemeParts themeParts = ThemeParts.builder()
                .parts(parentSave)
                .theme(themeByType)
                .position(command.position())
                .build();
        themePartsRepository.save(themeParts);

        List<MoruParts> childDatas = command.childData().stream()
                .filter(data -> data.isRepresentative() == false)
                .map(data -> MoruParts.builder()
                        .parentKey(parentSave.getKey())
                        .representative(false)
                        .status(MoruStatus.PENDING)
                        .name(command.partsName())
                        .width(command.width())
                        .height(command.height())
                        .purchaseInfo(command.getPurchaseInfos())
                        .imageUrl(data.partImageUrl())
                        .colorHexCode(data.colorhexCode())
                        .build()
                )
                .collect(Collectors.toList());
        moruPartsRepository.saveAll(childDatas);


        List<MoruPartsTag> partsTags = command.tags().stream()
                .map(data -> MoruPartsTag.builder()
                        .moruParts(parentSave)
                        .tag(data)
                        .build())
                .collect(Collectors.toList());

        moruPartsTagRepository.saveAll(partsTags);
    }

    @Description("기존의 파츠 정보를 soft delete 하고 다시 저장")
    @Override
    @Transactional
    public void modifyMoruParts(PartsCommand command) {
        MoruParts parentMoruParts = moruPartsRepository.findByKey(command.parentPartsKey())
                .orElseThrow(() -> new CommonRuntimeException(PartsErrorCode.NOT_FOUND_MORU_PARTS));

        // 부모 파츠 삭제
        parentMoruParts.deleteParts();

        // 자식 파츠 삭제
        List<MoruParts> allParts = moruPartsRepository.findAllByParentKey(command.parentPartsKey());
        allParts.forEach(MoruParts::deleteParts);

        // 테마 파츠 삭제
        List<Long> partsKeys = new ArrayList<>(Arrays.asList(command.parentPartsKey()));
        List<Long> childKeys = allParts.stream().map(Parts::getKey).toList();
        partsKeys.addAll(childKeys);

        List<ThemeParts> allThemeParts = themePartsRepository.findAllByParts_KeyIn(partsKeys);
        allThemeParts.forEach(ThemeParts::deleteThemeParts);

        // 파츠 태그 삭제
        List<MoruPartsTag> allTags = moruPartsTagRepository.findAllByMoruParts_Key(command.parentPartsKey());
        allTags.forEach(MoruPartsTag::deletePartsTag);

        // 새로 저장
        saveMoruParts(command);
    }

    @Transactional(readOnly = true)
    public Theme getThemeByType(ThemeType type) {
        return themeRepository.findByType(type)
                .orElseThrow(() -> new CommonRuntimeException(ThemeError.THEME_NOT_FOUND));
    }
}
