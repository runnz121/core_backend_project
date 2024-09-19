package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;

public interface BoardCommandUseCase {

    Boolean likeBoard(BoardLikeCommand command);
}
