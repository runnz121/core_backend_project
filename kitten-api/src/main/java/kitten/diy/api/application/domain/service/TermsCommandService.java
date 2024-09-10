package kitten.diy.api.application.domain.service;

import kitten.diy.api.application.port.in.TermsCommandUseCase;
import kitten.diy.api.application.port.in.command.TermsAgreementCommand;
import kitten.diy.api.application.port.out.TermsAgreementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TermsCommandService implements TermsCommandUseCase {

    private final TermsAgreementPort termsAgreementPort;

    @Override
    @Transactional
    public void agreeTerms(TermsAgreementCommand command) {

        termsAgreementPort.saveUserTermsAgreement();
    }
}
