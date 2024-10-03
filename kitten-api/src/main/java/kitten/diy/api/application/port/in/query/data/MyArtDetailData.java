package kitten.diy.api.application.port.in.query.data;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.board.consts.BoardPostStatus;
import kitten.core.coredomain.moru.consts.MoruSide;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import lombok.Builder;

import java.util.List;

@Builder
public record MyArtDetailData(

        @Description("모루 아이템 키")
        Long itemKey,

        @Description("공간 가로 길이")
        Integer width,

        @Description("공간 세로 길이")
        Integer height,

        @Description("작품 정면 이미지 URL")
        String frontImgUrl,

        @Description("작품 뒷면 이미지 URL")
        String backImgUrl,

        @Description("태그 정보 들")
        List<String> tags,

        @Description("글 설명")
        String comment,

        @Description("모루 인형 색상 코드")
        String colorHexCode,

        @Description("파츠 저장 정보")
        List<MyArtDetailData.PartInfo> partInfos,

        @Description("유저 정보")
        String userName,

        @Description("게시글 게시 상태")
        BoardPostStatus postStatus
) {

    @Builder
    public record PartInfo(

            @Description("파츠 키")
            Long partKey,

            @Description("파츠 x 좌표")
            Integer corX,

            @Description("파츠 y 좌표")
            Integer corY,

            @Description("파츠 z 좌표")
            Integer corZ,

            @Description("파츠 회전 정도")
            Integer rotation,

            @Description("파츠 앞/뒤 상태")
            MoruSide side,

            @Description("파츠 이미지 url")
            String partsImgUrl,

            @Description("유저가 조절한 파츠 가로길이")
            Integer customWidth,

            @Description("유저가 조절한 파츠 세로길이")
            Integer customHeight
    ) {
    }
}
