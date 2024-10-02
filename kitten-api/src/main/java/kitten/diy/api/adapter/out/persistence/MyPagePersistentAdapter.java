package kitten.diy.api.adapter.out.persistence;

import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.repository.BoardRepository;
import kitten.diy.api.application.port.out.MyPagePersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MyPagePersistentAdapter implements MyPagePersistentPort {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void deleteBoard(Long boardKey) {
        boardRepository.findByKeyAndDeletedIsFalse(boardKey).ifPresent(
                Board::deleteBoard
        );
    }
}
