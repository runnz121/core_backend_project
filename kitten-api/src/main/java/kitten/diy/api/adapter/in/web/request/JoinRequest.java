package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;

import java.util.List;

public record JoinRequest(

        @Description("이메일")
        String email,

        @Description("닉네임")
        String nickName,

        @Description("프로필 이미지 url")
        String profileImageUrl,

        @Description("핸드폰 번호")
        String phoneNumber,

        @Description("동의한 항목")
        List<Long> termsKeys
) {
}
