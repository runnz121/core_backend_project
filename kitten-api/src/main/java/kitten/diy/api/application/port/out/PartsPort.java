package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.PartsCommand;

public interface PartsPort {

    void saveMoruParts(PartsCommand command);

    void modifyMoruParts(PartsCommand command);

    void deleteMoruParts(Long parentPartKey);
}
