package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import lombok.Builder;

@Builder
public record TermsAgreementRequest(

        @Description(name = "이벤트 혜택 수신 동의")
        Boolean terms1,

        @Description(name = "뉴스레터 수신 동의")
        Boolean terms2,

        @Description(name = "개인 정보 수집동의 (필수)")
        Boolean terms3,

        @Description(name = "저작권, 초상권 침해에 대해 서비스 면책 사항 동의")
        Boolean terms4,

        @Description(name = "컨텐츠 활용 동의")
        Boolean terms5

) {
}
