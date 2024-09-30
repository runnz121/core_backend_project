package kitten.diy.api.application.port.in.command.command;

import kitten.core.corecommon.annotation.Description;
import kitten.core.coredomain.moru.entity.MoruParts;
import lombok.Builder;

import java.util.List;

@Builder
public record PartsRegisterCommand(

        @Description("파츠 이름(재료명)")
        String partsName,

        @Description("가로 실물 크기 (mm)")
        Integer width,

        @Description("세로 실물 크기 (mm)")
        Integer height,

        @Description("구입 상호처")
        List<String> purchaseInfos,

        @Description("태그 정보")
        List<String> tags,

        @Description("파츠 차일드 정보")
        List<PartsRegisterCommand.PartsChild> childData,

        @Description("등록 요청자")
        String registerUser
) {

    @Builder
    public record PartsChild(

            @Description("파츠 색깔 hexCode")
            String colorhexCode,

            @Description("파츠 이미지 url")
            String partImageUrl,

            @Description("대표 선택 여부")
            Boolean isRepresentative
    ) {
    }

    private static final String ADMIN_EMAIL = "diykitten.com@gmail.com";

    public String getPurchaseInfos() {
        return String.join(",", purchaseInfos);
    }

    public boolean isAdmin() {
        return registerUser.equals(ADMIN_EMAIL);
    }
}
