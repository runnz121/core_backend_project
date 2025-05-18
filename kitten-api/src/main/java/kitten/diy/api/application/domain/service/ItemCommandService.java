package kitten.diy.api.application.domain.service;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.adapter.out.model.ItemEventData;
import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.MoruCommand;
import kitten.diy.api.application.port.out.BoardPersistentPort;
import kitten.diy.api.application.port.out.ItemPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCommandService implements ItemCommandUseCase {

    private final ApplicationEventPublisher publisher;

    private final ItemPersistentPort itemPersistentPort;
    private final BoardPersistentPort boardPersistentPort;

    @Override
    @Transactional
    public void saveMoru(MoruCommand command) {

        // 1. 아이템 저장
        itemPersistentPort.saveMoru(command);

        // 2. 이벤트 발행으로 캐시 초기화
        publisher.publishEvent(ItemEventData.CREATE);
    }

    @Override
    @Transactional
    public void saveAvatar(AvatarCommand command) {
        // 아이템 저장
        MoruUserArtInfo savedArtInfo = itemPersistentPort.saveItem(command);
        // 게시글 저장
        boardPersistentPort.saveBoard(command, savedArtInfo);
    }

    @Override
    @Transactional
    public void modifyAvatar(AvatarCommand command,
                             Long boardKey) {

        // 모루 아이템 수정
        MoruUserArtInfo savedArtInfo = itemPersistentPort.modifyItem(command, boardKey);

        // 게시글 수정
        boardPersistentPort.modifyBoard(command, savedArtInfo, boardKey);
    }
}
