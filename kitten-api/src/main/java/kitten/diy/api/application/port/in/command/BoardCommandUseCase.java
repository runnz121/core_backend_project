package kitten.diy.api.application.port.in.command;

import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;

public interface BoardCommandUseCase {

    Boolean likeBoard(BoardLikeCommand command);

    void deleteBoard(Long boardKey);

    void updateViewCount(CurrentAccount currentAccount, Long boardKey);

}
