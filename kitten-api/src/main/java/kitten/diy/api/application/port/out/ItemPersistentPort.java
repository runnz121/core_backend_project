package kitten.diy.api.application.port.out;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.MoruCommand;

public interface ItemPersistentPort {

    MoruUserArtInfo saveItem(AvatarCommand command);

    MoruUserArtInfo modifyItem(AvatarCommand command, Long boardKey);

    void saveMoru(MoruCommand command);
}
