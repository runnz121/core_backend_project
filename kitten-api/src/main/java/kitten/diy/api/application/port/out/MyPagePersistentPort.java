package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.MyProfileCommand;

public interface MyPagePersistentPort {

    void deleteBoard(Long boardKey);

    void modifyMyProfile(MyProfileCommand command);
}
