package kitten.diy.api.adapter.out.persistence;

import kitten.core.corecommon.config.exception.CommonRuntimeException;
import kitten.core.coredomain.board.entity.BoardItem;
import kitten.core.coredomain.board.repository.BoardItemRepository;
import kitten.core.coredomain.moru.entity.Moru;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.moru.entity.MoruUserPart;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.core.coredomain.moru.repository.MoruPartsRepository;
import kitten.core.coredomain.moru.repository.MoruRepository;
import kitten.core.coredomain.moru.repository.MoruUsePartRepository;
import kitten.core.coredomain.moru.repository.MoruUserArtInfoRepository;
import kitten.core.coredomain.user.entity.Users;
import kitten.core.coredomain.user.repository.UsersRepository;
import kitten.diy.api.adapter.out.error.BoardErrorCode;
import kitten.diy.api.adapter.out.error.ItemErrorCode;
import kitten.diy.api.adapter.out.error.PartsErrorCode;
import kitten.diy.api.adapter.out.error.UserErrorCode;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.out.ItemPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemPersistenceAdapter implements ItemPersistentPort {

    private final UsersRepository usersRepository;
    private final MoruRepository moruRepository;
    private final MoruPartsRepository moruPartsRepository;
    private final MoruUsePartRepository moruUsePartRepository;
    private final MoruUserArtInfoRepository moruUserArtInfoRepository;

    private final BoardItemRepository boardItemRepository;

    @Override
    @Transactional
    public MoruUserArtInfo saveItem(AvatarCommand command) {

        Users users = getUser(command.userName());
        Moru moru = getMoru(command.itemKey());

        MoruUserArtInfo artInfo = MoruUserArtInfo.builder()
                .users(users)
                .moru(moru)
                .moruColorHexCode(command.colorHexCode())
                .width(command.width())
                .height(command.height())
                .frontImgUrl(command.frontImgUrl())
                .backImgUrl(command.backImgUrl())
                .build();

        moruUserArtInfoRepository.save(artInfo);

        command.partInfos().forEach(
                partInfo -> {
                    MoruParts parts = getMoruParts(partInfo.partKey());
                    MoruUserPart userArt = MoruUserPart.builder()
                            .moruUserArtInfo(artInfo)
                            .moruParts(parts)
                            .corX(partInfo.corX())
                            .corY(partInfo.corY())
                            .corZ(partInfo.corZ())
                            .rotation(partInfo.rotation())
                            .side(partInfo.side())
                            .customWidth(partInfo.customWidth())
                            .customHeight(partInfo.customHeight())
                            .build();

                    moruUsePartRepository.save(userArt);
                }
        );
        return artInfo;
    }

    @Override
    @Transactional
    public MoruUserArtInfo modifyItem(AvatarCommand command,
                                      Long boardKey) {
        // 기존 유저 작품 삭제
        deleteMoruUserArtInfo(command, boardKey);

        // 아이템 저장
        return saveItem(command);
    }

    private void deleteMoruUserArtInfo(AvatarCommand command, Long boardKey) {
        BoardItem boardItem = boardItemRepository.findByBoard_KeyAndDeletedIsFalse(boardKey)
                .orElseThrow(() -> new CommonRuntimeException(BoardErrorCode.BOARD_ITEM_NOT_FOUND));
        // artiInfo 삭제
        MoruUserArtInfo userArtInfo = boardItem.getUserArtInfo();
        userArtInfo.deleteArtInfo();
        // partsInfo 삭제
        command.partInfos().forEach(
                partInfo -> {
                    MoruParts parts = getMoruParts(partInfo.partKey());
                    moruUsePartRepository.findByMoruPartsAndMoruUserArtInfo(parts, userArtInfo).ifPresent(
                            MoruUserPart::deleteUserPart
                    );
                }
        );
    }

    @Transactional(readOnly = true)
    public Moru getMoru(Long moruKey) {
        return moruRepository.findById(moruKey)
                .orElseThrow(() -> new CommonRuntimeException(ItemErrorCode.NOT_FOUND_MORU));
    }

    @Transactional(readOnly = true)
    public MoruParts getMoruParts(Long moruPartsKey) {
        return moruPartsRepository.findById(moruPartsKey)
                .orElseThrow(() -> new CommonRuntimeException(PartsErrorCode.NOT_FOUND_MORU_PARTS));
    }

    @Transactional(readOnly = true)
    public Users getUser(String userEmail) {
        return usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CommonRuntimeException(UserErrorCode.USER_NOT_EXISTS));
    }

}
