package kitten.diy.api.adapter.out.model;

import com.querydsl.core.annotations.QueryProjection;

public record MyLikeRankInfo(

        Long likeBoardCount,

        Long boardKey,

        Long userKey
) {

    @QueryProjection
    public MyLikeRankInfo {
    }
}
