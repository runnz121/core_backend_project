package kitten.diy.api.application.port.in;

import kitten.diy.api.application.port.in.command.TermsAgreementCommand;

public interface TermsCommandUseCase {

    void agreeTerms(TermsAgreementCommand command);
}
