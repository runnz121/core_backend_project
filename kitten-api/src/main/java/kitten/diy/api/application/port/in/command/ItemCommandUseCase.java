package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import kitten.diy.api.application.port.in.command.command.MoruCommand;

public interface ItemCommandUseCase {

    void saveMoru(MoruCommand command);

    void saveAvatar(AvatarCommand command);

    void modifyAvatar(AvatarCommand command, Long boardKey);
}
