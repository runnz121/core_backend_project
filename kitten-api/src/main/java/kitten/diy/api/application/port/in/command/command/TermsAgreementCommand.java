package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.terms.entity.Terms;
import kitten.core.coredomain.terms.entity.TermsAgreement;
import kitten.core.coredomain.user.entity.Users;
import lombok.Builder;

import java.util.List;

@Builder
public record TermsAgreementCommand(

        List<Long> agreementTermsKeys,

        String userEmail
) {
    public static TermsAgreementCommand of(String userEmail,
                                           List<Long> agreementTermsKeys) {
        return TermsAgreementCommand.builder()
                .userEmail(userEmail)
                .agreementTermsKeys(agreementTermsKeys)
                .build();
    }

    public TermsAgreement toEntity(Terms terms,
                                   Users users) {
        return TermsAgreement.builder()
                .terms(terms)
                .users(users)
                .build();
    }
}
