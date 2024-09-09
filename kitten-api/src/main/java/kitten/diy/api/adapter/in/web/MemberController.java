package kitten.diy.api.adapter.in.web;

import kitten.diy.api.adapter.in.web.request.TermsAgreementRequest;
import kitten.diy.api.application.port.in.MemberCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandUseCase memberCommandUseCase;

    @PostMapping("/join")
    public void termsAgreement(@RequestBody TermsAgreementRequest request) {
        memberCommandUseCase.agreeTerms(request.toCommand());
    }
}
