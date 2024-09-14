package kitten.diy.api.adapter.in.web;

import kitten.core.corecommon.security.jwt.AccessAccount;
import kitten.core.corecommon.security.jwt.CurrentAccount;
import kitten.diy.api.adapter.in.web.request.TermsAgreementRequest;
import kitten.diy.api.application.port.in.command.TermsCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terms")
@RequiredArgsConstructor
public class TermsController {

    private final TermsCommandUseCase termsCommandUseCase;

    @PostMapping("/agreement")
    public void termsAgreement(@AccessAccount CurrentAccount account,
                               @RequestBody TermsAgreementRequest request) {
        termsCommandUseCase.agreeTerms(request.toCommand(account.getUserEmail()));
    }
}
