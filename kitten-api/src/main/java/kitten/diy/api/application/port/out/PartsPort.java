package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.PartsRegisterCommand;

public interface PartsPort {

    void saveMoruParts(PartsRegisterCommand command);
}
