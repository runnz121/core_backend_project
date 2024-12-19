package kitten.diy.api.application.port.in.command.command;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import lombok.Builder;
import org.springframework.lang.Nullable;

import java.util.List;

@Builder
public record PartsCommand(

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
        ThemeType theme,

        @Description("부위")
        ThemePosition position,

        @Description("구입 상호처")
        List<String> purchaseInfos,

        @Description("태그 정보")
        List<String> tags,

        @Description("파츠 차일드 정보")
        List<PartsCommand.PartsChild> childData,

        @Description("등록 요청자")
        String registerUser
) {

    @Builder
    public record PartsChild(

            @Nullable
            @Description("수정 api 로 호출시에만 사용")
            Long childPartsKey,

            @Description("파츠 색깔 hexCode")
            String colorhexCode,

            @Description("파츠 이미지 url")
            String partImageUrl,

            @Description("대표 선택 여부")
            Boolean isRepresentative
    ) {
    }

    public String getPurchaseInfos() {
        return String.join(",", purchaseInfos);
    }
}
