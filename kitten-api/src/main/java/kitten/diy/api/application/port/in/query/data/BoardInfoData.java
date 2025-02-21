package kitten.diy.api.application.port.in.query.data;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.board.consts.BoardType;
import kitten.core.coredomain.user.entity.Users;
import kitten.diy.api.adapter.out.model.BoardQueryData;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
public record BoardInfoData(

        @Description("게시글 key")
        Long boardKey,

        @Description("대표 이미지 url")
        String imageUrl,

        @Description("게시글 타입")
        BoardType type,

        @Description("생성일자")
        LocalDateTime createTime,

        @Description("찜 갯수")
        Integer likeCount,

        @Description("조회수")
        Integer viewCount,

        @Description("내가 찜한 여부")
        Boolean isMyLike,

        @Description("게시글 유저 닉네임")
        String nickName,

        @Description("게시글 유저 프로필 이미지 url")
        String profileImgUrl,

        @Description("태그")
        List<String> tags
) {

    public static BoardInfoData of(BoardQueryData data,
                                   List<String> tags,
                                   Boolean isMyLike,
                                   Users boardCreateUser) {
        return BoardInfoData.builder()
                .boardKey(data.boardKey())
                .imageUrl(data.imageUrl())
                .type(data.type())
                .createTime(data.createTime())
                .likeCount(data.likeCount().intValue())
                .viewCount(data.viewCount().intValue())
                .tags(tags)
                .nickName(Optional.ofNullable(boardCreateUser).map(Users::getNickName).orElse(""))
                .profileImgUrl(Optional.ofNullable(boardCreateUser).map(Users::getProfileImgUrl).orElse(""))
                .isMyLike(isMyLike)
                .build();
    }
}
