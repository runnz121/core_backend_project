package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.ItemCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemCommandService implements ItemCommandUseCase {

    @Transactional
    public void registerMoruParts() {

    }
}
