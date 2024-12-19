package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.LogoutCommand;
import kitten.diy.api.application.port.in.query.data.LogoutResultData;

public interface AuthCommandUseCase {

    LogoutResultData logOut(LogoutCommand command);
}
