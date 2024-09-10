package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.diy.api.application.port.in.command.TermsAgreementCommand;
import lombok.Builder;

@Builder
public record TermsAgreementRequest(

        @Description(value = "이벤트 혜택 수신 동의")
        Boolean terms1,

        @Description(value = "뉴스레터 수신 동의")
        Boolean terms2,

        @Description(value = "개인 정보 수집동의 (필수)")
        Boolean terms3,

        @Description(value = "저작권, 초상권 침해에 대해 서비스 면책 사항 동의")
        Boolean terms4,

        @Description(value = "컨텐츠 활용 동의")
        Boolean terms5

) {

        public TermsAgreementCommand toCommand(String userEmail) {
                return TermsAgreementCommand.builder()
                        .terms1(terms1)
                        .terms2(terms2)
                        .terms3(terms3)
                        .terms4(terms4)
                        .terms5(terms5)
                        .build();
        }
}
