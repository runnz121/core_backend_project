package kitten.diy.api.application.port.in.query.data;

import kitten.core.corecommon.annotation.Description;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardInfoData(

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

    public static BoardInfoData of(BoardQueryData data) {
        return BoardInfoData.builder()
                .boardKey(data.boardKey())
                .imageUrl(data.imageUrl())
                .createTime(data.createTime())
                .likeCount(data.likeCount())
                .viewCount(data.viewCount())
                .build();
    }
}
