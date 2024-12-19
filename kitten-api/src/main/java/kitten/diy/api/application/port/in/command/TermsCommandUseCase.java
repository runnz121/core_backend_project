package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.port.in.command.command.TermsAgreementCommand;

public interface TermsCommandUseCase {

    void agreeTerms(TermsAgreementCommand command);
}
