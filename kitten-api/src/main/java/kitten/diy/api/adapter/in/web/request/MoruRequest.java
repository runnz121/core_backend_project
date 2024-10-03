package kitten.diy.api.adapter.in.web.request;

import kitten.core.coredomain.theme.consts.ThemeType;
import kitten.diy.api.application.port.in.command.command.MoruCommand;

public record MoruRequest(

        String name,

        String themeType,

        String frontImgUrl,

        String backImgUrl,

        Integer width,

        Integer height
) {

    public MoruCommand toCommand() {
        return MoruCommand.builder()
                .name(name)
                .themeType(ThemeType.valueOf(themeType))
                .frontImgUrl(frontImgUrl)
                .backImgUrl(backImgUrl)
                .width(width)
                .height(height)
                .build();
    }
}
