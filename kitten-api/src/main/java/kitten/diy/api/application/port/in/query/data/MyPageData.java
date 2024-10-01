package kitten.diy.api.application.port.in.query.data;

import kitten.core.corecommon.annotation.Description;
import lombok.Builder;

@Builder
public record MyPageData(

        @Description("닉네임")
        String nickName,

        @Description("프로필 이미지 url")
        String profileImgUrl,

        @Description("받은 좋아요 갯수")
        Integer myLikeCount,

        @Description("좋아요 순위")
        Integer myLikeRank,

        @Description("조회수 순위")
        Integer myViewRank
) {
}
