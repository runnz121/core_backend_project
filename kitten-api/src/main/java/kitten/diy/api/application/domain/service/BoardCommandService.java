package kitten.diy.api.application.domain.service;

import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.application.port.in.command.BoardCommandUseCase;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;
import kitten.diy.api.application.port.out.BoardPersistentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCommandUseCase {

    private final BoardPersistentPort boardPersistentPort;

    @Override
    public Boolean likeBoard(BoardLikeCommand command) {
        return boardPersistentPort.likeBoard(command);
    }

    @Override
    public void deleteBoard(Long boardKey) {
        boardPersistentPort.deleteBoard(boardKey);
    }

    @Override
    public void updateViewCount(CurrentAccount currentAccount,
                                Long boardKey) {
        boardPersistentPort.updateBoardViewCount(currentAccount, boardKey);
    }
}
