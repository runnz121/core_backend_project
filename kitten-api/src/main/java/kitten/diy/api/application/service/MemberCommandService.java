package kitten.diy.api.application.service;

import kitten.diy.api.application.port.in.MemberCommandUseCase;
import kitten.diy.api.application.port.in.command.JoinCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService implements MemberCommandUseCase {

    private final MemberCommandUseCase memberCommandUseCase;

    @Override
    @Transactional
    public void agreeTerms(JoinCommand command) {

    }
}
