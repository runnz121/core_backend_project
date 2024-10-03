package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.MyProfileCommand;

public interface MyPageCommandUseCase {

    void deleteMyBoard(Long boardKey);

    void modifyMyProfile(MyProfileCommand command);
}
