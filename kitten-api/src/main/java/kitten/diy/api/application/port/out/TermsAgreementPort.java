package kitten.diy.api.application.port.out;

import kitten.diy.api.application.port.in.command.command.TermsAgreementCommand;

public interface TermsAgreementPort {

    void saveUserTermsAgreement(TermsAgreementCommand command);
}
