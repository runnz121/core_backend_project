package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.diy.api.application.port.in.command.command.PartsRegisterCommand;

import java.util.List;

public record MoruPartsRequest(

        @Description("파츠 이름(재료명)")
        String partsName,

        @Description("가로 실물 크기 (mm)")
        Integer width,

        @Description("세로 실물 크기 (mm)")
        Integer height,
        @Description("테마")
        String theme,

        @Description("부위")
        String position,

        @Description("구입 상호처")
        List<String> purchaseInfos,

        @Description("태그 정보")
        List<String> tags,

        @Description("파츠 차일드 정보")
        List<MoruPartsChild> childData
) {

        public record MoruPartsChild(

                @Description("파츠 색깔 hexCode")
                String colorhexCode,

                @Description("파츠 이미지 url")
                String partImageUrl,

                @Description("대표 선택 여부")
                Boolean isRepresentative
        ) {
        }

        public PartsRegisterCommand toCommand(String user) {
                List<PartsRegisterCommand.PartsChild> childDatas = childData.stream()
                        .map(child ->
                                PartsRegisterCommand.PartsChild.builder()
                                        .colorhexCode(child.colorhexCode)
                                        .partImageUrl(child.partImageUrl)
                                        .isRepresentative(child.isRepresentative)
                                        .build())
                        .toList();
                return PartsRegisterCommand.builder()
                        .partsName(partsName)
                        .width(width)
                        .height(height)
                        .theme(ThemeType.valueOf(theme))
                        .position(ThemePosition.valueOf(position))
                        .purchaseInfos(purchaseInfos)
                        .tags(tags)
                        .childData(childDatas)
                        .registerUser(user)
                        .build();
        }
}
