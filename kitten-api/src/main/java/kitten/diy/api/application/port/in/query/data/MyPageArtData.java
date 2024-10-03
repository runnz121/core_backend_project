package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.board.consts.BoardPostStatus;

public record MyPageArtData(

        Long boardKey,

        String imageUrl,

        BoardPostStatus status
) {
}
