package kitten.diy.api.adapter.in.web.request;

import kitten.core.corecommon.annotation.Description;
import kitten.diy.api.application.port.in.command.command.MoruPartsRegisterCommand;

import java.util.List;

public record MoruPartsRequest(

        @Description("파츠 이름")
        String partsName,

        @Description("가로 실물 크기 (mm)")
        Integer width,

        @Description("세로 실물 크기 (mm)")
        Integer height,

        @Description("파츠 색깔 hexCode")
        String colorhexCode,

        @Description("구입 상호처")
        List<String> purchaseInfos
) {

        public MoruPartsRegisterCommand toCommand(String user) {
                return null;
        }

}
