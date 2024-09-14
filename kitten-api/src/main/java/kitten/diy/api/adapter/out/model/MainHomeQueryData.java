package kitten.diy.api.adapter.out.model;

import com.querydsl.core.annotations.QueryProjection;
import kitten.core.corecommon.annotation.Description;

import java.time.LocalDateTime;

public record MainHomeQueryData(

        @Description("게시글 key")
        Long boardKey,

        @Description("대표 이미지 url")
        String imageUrl,

        @Description("생성일자")
        LocalDateTime createTime,

        @Description("찜 갯수")
        Integer likeCount,

        @Description("조회수")
        Integer viewCount

) {

        @QueryProjection
        public MainHomeQueryData{

        }
}
