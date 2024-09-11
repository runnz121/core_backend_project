package kitten.diy.api.adapter.in.web.request;

import kitten.diy.api.application.port.in.command.TermsAgreementCommand;
import lombok.Builder;

import java.util.List;

@Builder
public record TermsAgreementRequest(

        List<Long> agreementTermsKeys

) {

        public TermsAgreementCommand toCommand(String userEmail) {
                return TermsAgreementCommand.builder()
                        .agreementTermsKeys(agreementTermsKeys)
                        .userEmail(userEmail)
                        .build();
        }
}
