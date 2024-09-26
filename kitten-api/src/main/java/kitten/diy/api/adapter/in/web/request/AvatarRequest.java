package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.moru.consts.MoruSide;
import kitten.diy.api.application.port.in.command.command.AvatarCommand;
import lombok.Builder;

import java.util.List;

@Builder
public record AvatarRequest(

        @Description("모루 아이템 키")
        Long itemKey,

        @Description("공간 가로 길이")
        Integer width,

        @Description("공간 세로 길이")
        Integer height,

        @Description("대표사진 이미지 URL")
        String repImgUrl,

        @Description("태그 정보 들")
        List<String> tags,

        @Description("글 설명")
        String comment,

        @Description(value = "포스팅 여부", example = "true : 포스팅 | false : 임시저장")
        Boolean isPost,

        @Description("모루 인형 색상 코드")
        String colorHexCode,

        @Description("파츠 저장 정보")
        List<PartInfo> partInfos

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

            @Description(value = "파츠 앞/뒤 상태", example = "[FRONT, BACK]")
            String side
    ) {

        public AvatarCommand.PartInfo toInfoCommand() {
            return AvatarCommand.PartInfo.builder()
                    .partKey(partKey)
                    .corX(corX)
                    .corY(corY)
                    .corZ(corZ)
                    .rotation(rotation)
                    .side(MoruSide.valueOf(side))
                    .build();
        }
    }

    public AvatarCommand toCommand(String userName) {
        return AvatarCommand.builder()
                .itemKey(itemKey)
                .width(width)
                .height(height)
                .repImgUrl(repImgUrl)
                .tags(tags)
                .comment(comment)
                .colorHexCode(colorHexCode)
                .userName(userName)
                .partInfos(partInfos.stream()
                        .map(PartInfo::toInfoCommand)
                        .toList())
                .build();
    }

}
