package kitten.diy.api.application.port.out;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;

public interface BoardPersistentPort {

    boolean likeBoard(BoardLikeCommand command);

    void saveBoard(AvatarCommand command, MoruUserArtInfo savedArtInfo);
}
