package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.PartsCommandUseCase;
import kitten.diy.api.application.port.in.command.command.PartsRegisterCommand;
import kitten.diy.api.application.port.out.PartsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartsCommandService implements PartsCommandUseCase {

    private final PartsPort partsPort;

    @Override
    @Transactional
    public void registerMoruParts(PartsRegisterCommand command) {
        partsPort.saveMoruParts(command);
    }
}
