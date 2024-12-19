package kitten.diy.api.application.port.in.query.data;

import lombok.Builder;

import java.util.List;

@Builder
public record BoardPartsInfo(

        String partsName,

        Integer width,

        Integer height,

        String colorHexCode,

        String partImgUrl,

        String partsUserNickName,

        List<String> purchaseInfos,

        List<String> tags
) {
}
