package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.moru.consts.MoruStatus;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruPartsTag;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruPartsTagRepository;
import kitten.diy.api.adapter.out.error.PartsErrorCode;
import kitten.diy.api.application.port.in.command.command.PartsRegisterCommand;
import kitten.diy.api.application.port.out.PartsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartsCommandAdapter implements PartsPort {

    private final MoruPartsRepository moruPartsRepository;
    private final MoruPartsTagRepository moruPartsTagRepository;

    @Override
    @Transactional
    public void saveMoruParts(PartsRegisterCommand command) {

        PartsRegisterCommand.PartsChild parentData = command.childData().stream()
                .filter(PartsRegisterCommand.PartsChild::isRepresentative)
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
}
