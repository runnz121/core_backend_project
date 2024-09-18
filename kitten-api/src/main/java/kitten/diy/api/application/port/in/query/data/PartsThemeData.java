package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import lombok.Builder;

import java.util.List;

@Builder
public record PartsThemeData(

        @Description("테마 타입")
        ThemeType themeType,

        @Description("테마 포지션")
        ThemePosition themePosition,

        @Description("부모 데이터")
        List<PartsData> parentData

) {

    public static PartsThemeData createPartsThemeData(ThemeType themeType,
                                                      ThemePosition position,
                                                      List<PartsData> partsData) {
        return PartsThemeData.builder()
                .themeType(themeType)
                .themePosition(position)
                .parentData(partsData)
                .build();
    }

    @Builder
    public record PartsData(

            Long partsKey,

            Long parentKey,

            String title,

            Integer width,

            Integer height,

            List<String> tags,

            String colorHexCode,

            @Description("자식 파츠 데이터")
            List<PartsData> childData
    ) {

        public static PartsData createMoruParts(MoruParts moruParts,
                                                List<PartsData> childData,
                                                List<String> tags,
                                                boolean isChild) {
            return PartsData.builder()
                    .partsKey(moruParts.getKey())
                    .parentKey(isChild ? moruParts.getParentKey() :  null)
                    .childData(isChild ? null : childData)
                    .tags(isChild ? null : tags)
                    .title(moruParts.getName())
                    .width(moruParts.getWidth())
                    .height(moruParts.getHeight())
                    .colorHexCode(moruParts.getColorHexCode())
                    .build();
        }
    }
}
