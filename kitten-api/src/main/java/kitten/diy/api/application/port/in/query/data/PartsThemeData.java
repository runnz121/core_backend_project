package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.config.annotation.Description;
import kitten.core.coredomain.moru.entity.MoruParts;
import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.core.coredomain.theme.entity.Theme;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public record PartsThemeData(

        @Description("테마 타입")
        ThemeType themeType,

        @Description("테마 포지션")
        ThemePosition themePosition,

        @Description("부모 데이터")
        List<PartsData> parentData

) {

    public static PartsThemeData createPartsThemeData(Theme theme,
                                                      ThemePosition position,
                                                      List<PartsData> partsData) {
        return PartsThemeData.builder()
                .themeType(getThemeType(theme))
                .themePosition(position)
                .parentData(partsData)
                .build();
    }

    private static ThemeType getThemeType(Theme theme) {
        return Objects.isNull(theme) ? ThemeType.ALL : theme.getType();
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

            @Description("대표 여부")
            Boolean isRepresentative,

            @Description("파츠 이미지 url")
            String partsImageUrl,

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
                    .isRepresentative(moruParts.getRepresentative())
                    .partsImageUrl(moruParts.getImageUrl())
                    .build();
        }
    }
}
