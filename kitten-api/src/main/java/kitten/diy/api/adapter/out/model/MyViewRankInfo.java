package kitten.diy.api.adapter.out.model;

import com.querydsl.core.annotations.QueryProjection;

public record MyViewRankInfo(

        Long viewBoardCount,

        Long boardKey,

        Long userKey
) {

    @QueryProjection
    public MyViewRankInfo {
    }
}
