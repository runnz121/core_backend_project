package kitten.diy.api.application.port.out;

import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.BoardLikeCommand;

public interface BoardPersistentPort {

    boolean likeBoard(BoardLikeCommand command);

    void saveBoard(AvatarCommand command, MoruUserArtInfo savedArtInfo);

    void deleteBoard(Long boardKey);

    void modifyBoard(AvatarCommand command, MoruUserArtInfo savedArtInfo, Long boardKey);

    void updateBoardViewCount(CurrentAccount account, Long boardKey);
}
