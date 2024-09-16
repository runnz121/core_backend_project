package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.board.consts.BoardType;
import kitten.core.coredomain.board.entity.Board;
import lombok.Builder;

import java.util.List;

@Builder
public record BoardDetailData(

        String nickName,

        String comment,

        String imageUrl,

        Integer likeCounts,

        Integer viewCounts,

        BoardType type,

        List<String> tags
) {

    public static BoardDetailData of(Board board,
                                     String imageUrl,
                                     Integer likeCounts,
                                     Integer viewCounts,
                                     List<String> tags) {
        return BoardDetailData.builder()
                .nickName(board.getWriterNickName())
                .comment(board.getComment())
                .type(board.getType())
                .imageUrl(imageUrl)
                .likeCounts(likeCounts)
                .viewCounts(viewCounts)
                .tags(tags)
                .build();
    }
}
