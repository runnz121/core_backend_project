package kitten.diy.api.application.port.in.command;

import lombok.Builder;

@Builder
public record TermsAgreementCommand(

        Boolean terms1,

        Boolean terms2,

        Boolean terms3,

        Boolean terms4,

        Boolean terms5,

        String userEmail
) {
}
