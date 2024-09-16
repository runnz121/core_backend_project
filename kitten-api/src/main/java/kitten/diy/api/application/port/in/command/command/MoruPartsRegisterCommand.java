package kitten.diy.api.application.port.in.command.command;

import lombok.Builder;

import java.util.List;

@Builder
public record MoruPartsRegisterCommand(

        String partsName,

        Integer width,

        Integer height,

        String colorhexCode,

        List<String> purchaseInfos,

        String registerUser
) {
}
