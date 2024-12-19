package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.command.JoinCommandUseCase;
import kitten.diy.api.application.port.in.command.command.JoinCommand;
import kitten.diy.api.application.port.in.command.command.TermsAgreementCommand;
import kitten.diy.api.application.port.out.JoinPersistentPort;
import kitten.diy.api.application.port.out.JoinTokenPort;
import kitten.diy.api.application.port.out.TermsAgreementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinCommandService implements JoinCommandUseCase {

    private final JoinPersistentPort joinPersistentPort;
    private final TermsAgreementPort termsAgreementPort;
    private final JoinTokenPort joinTokenPort;

    @Override
    @Transactional
    public String joinUser(JoinCommand command) {
        joinPersistentPort.saveNewUser(command);
        termsAgreementPort.saveUserTermsAgreement(TermsAgreementCommand.of(command.email(), command.termsKeys()));
        return joinTokenPort.createToken(command);
    }
}
