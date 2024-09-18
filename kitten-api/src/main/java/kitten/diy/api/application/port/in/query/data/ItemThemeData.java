package kitten.diy.api.application.port.in.query.data;

import kitten.core.coredomain.moru.entity.Moru;
import lombok.Builder;

@Builder
public record ItemThemeData(

        String name,

        Integer width,

        Integer height,

        String frontImgUrl,

        String backImgUrl
) {

    public static final ItemThemeData EMPTY = ItemThemeData.builder().build();

    public static ItemThemeData creteMoruItemData(Moru moru) {
        return ItemThemeData.builder()
                .name(moru.getName())
                .width(moru.getWidth())
                .height(moru.getHeight())
                .frontImgUrl(moru.getFrontImageUrl())
                .backImgUrl(moru.getBackImageUrl())
                .build();
    }
}
