package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.JoinCommand;

public interface JoinCommandUseCase {

    String joinUser(JoinCommand command);
}
