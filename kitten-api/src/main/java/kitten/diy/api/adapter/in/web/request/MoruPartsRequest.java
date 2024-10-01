package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.diy.api.application.port.in.command.command.PartsCommand;
import org.springframework.lang.Nullable;

import java.util.List;

public record MoruPartsRequest(

        @Nullable
        @Description("수정 api 로 호출시에만 사용")
        Long parentPartsKey,

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

                @Nullable
                @Description("수정 api 로 호출시에만 사용")
                Long childPartsKey,

                @Description("파츠 색깔 hexCode")
                String colorHexCode,

                @Description("파츠 이미지 url")
                String partImageUrl,

                @Description("대표 선택 여부")
                Boolean isRepresentative
        ) {
        }

        public PartsCommand toCommand(String user) {
                List<PartsCommand.PartsChild> childDatas = childData.stream()
                        .map(child ->
                                PartsCommand.PartsChild.builder()
                                        .childPartsKey(child.childPartsKey)
                                        .colorhexCode(child.colorHexCode)
                                        .partImageUrl(child.partImageUrl)
                                        .isRepresentative(child.isRepresentative)
                                        .build())
                        .toList();
                return PartsCommand.builder()
                        .parentPartsKey(parentPartsKey)
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
