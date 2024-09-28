package kitten.diy.api.application.domain.service;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.out.BoardPersistentPort;
import kitten.diy.api.application.port.out.ItemPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCommandService implements ItemCommandUseCase {

    private final ItemPersistentPort itemPersistentPort;
    private final BoardPersistentPort boardPersistentPort;

    @Override
    @Transactional
    public void saveAvatar(AvatarCommand command) {
        // 아이템 저장
        MoruUserArtInfo savedArtInfo = itemPersistentPort.saveItem(command);
        // 게시글 저장
        boardPersistentPort.saveBoard(command, savedArtInfo);
    }
}
