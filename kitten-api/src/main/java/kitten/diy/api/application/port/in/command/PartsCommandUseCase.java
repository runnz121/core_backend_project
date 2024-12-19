package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.PartsCommand;

public interface PartsCommandUseCase {

    void registerMoruParts(PartsCommand command);

    void modifyMoruParts(PartsCommand command);

    void deleteMoruParts(Long parentPartsKey);
}
