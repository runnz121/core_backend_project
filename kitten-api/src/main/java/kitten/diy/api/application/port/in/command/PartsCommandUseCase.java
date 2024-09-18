package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.PartsRegisterCommand;

public interface PartsCommandUseCase {

    void registerMoruParts(PartsRegisterCommand command);
}
