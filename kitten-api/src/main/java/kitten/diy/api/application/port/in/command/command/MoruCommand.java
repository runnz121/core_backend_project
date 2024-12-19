package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.theme.consts.ThemeType;
import lombok.Builder;

@Builder
public record MoruCommand(

        String name,

        ThemeType themeType,

        String frontImgUrl,

        String backImgUrl,

        Integer width,

        Integer height

) {
}
