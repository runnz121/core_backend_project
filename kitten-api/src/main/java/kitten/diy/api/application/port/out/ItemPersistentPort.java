package kitten.diy.api.application.port.out;

import kitten.core.coredomain.moru.entity.MoruUserArtInfo;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;

public interface ItemPersistentPort {

    MoruUserArtInfo saveItem(AvatarCommand command);
}
