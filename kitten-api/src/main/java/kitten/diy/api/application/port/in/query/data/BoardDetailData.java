package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.board.consts.BoardType;
import kitten.core.coredomain.board.entity.Board;
import kitten.core.coredomain.board.entity.BoardImage;
import lombok.Builder;

import java.util.List;
import java.util.stream.Stream;

@Builder
public record BoardDetailData(

        String nickName,

        String comment,

        String frontImgUrl,

        String backImgUrl,

        Integer likeCounts,

        Integer viewCounts,

        BoardType type,

        Boolean isMyLike,

        List<String> tags
) {

    public static BoardDetailData of(Board board,
                                     List<BoardImage> images,
                                     Integer likeCounts,
                                     Integer viewCounts,
                                     List<String> tags,
                                     Boolean isMyLike
    ) {
        return BoardDetailData.builder()
                .nickName(board.getWriterNickName())
                .comment(board.getComment())
                .type(board.getType())
                .isMyLike(isMyLike)
                .frontImgUrl(
                        Stream.ofNullable(images)
                        .flatMap(List::stream)
                        .filter(BoardImage::getRepresentative)
                        .map(BoardImage::getImageUrl).findFirst()
                        .orElse("")
                )
                .backImgUrl(
                        Stream.ofNullable(images)
                        .flatMap(List::stream)
                        .filter(image -> !image.getRepresentative())
                        .map(BoardImage::getImageUrl)
                        .findFirst()
                        .orElse(""))
                .likeCounts(likeCounts)
                .viewCounts(viewCounts)
                .tags(tags)
                .build();
    }
}
