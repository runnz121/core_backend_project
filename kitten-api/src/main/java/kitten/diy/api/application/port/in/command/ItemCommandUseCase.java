package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.AvatarCommand;

public interface ItemCommandUseCase {

    void saveAvatar(AvatarCommand command);

    void modifyAvatar(AvatarCommand command, Long boardKey);
}
