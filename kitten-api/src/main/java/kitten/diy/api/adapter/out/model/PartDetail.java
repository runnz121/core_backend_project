package kitten.diy.api.adapter.out.model;

import kitten.core.coredomain.theme.consts.ThemePosition;
import kitten.core.coredomain.theme.consts.ThemeType;
import lombok.Builder;

import java.util.List;

@Builder
public record PartDetail(

        Long parentPartKey,

        String name,

        String imageUrl,

        Integer width,

        Integer height,

        String colorHexCode,

        List<String> tags,

        List<ChildPartDetail> childPartDetails,

        List<String> purchaseInfos,

        List<Position> themePosition
) {

    @Builder
    public record ChildPartDetail(

            Long childPartsKey,

            String imageUrl,

            String colorHexCode
    ) {
    }

    @Builder
    public record Position(

            ThemeType type,

            ThemePosition position
    ) {
    }
}
