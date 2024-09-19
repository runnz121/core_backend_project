package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;

public interface BoardPersistentPort {

    boolean likeBoard(BoardLikeCommand command);
}
