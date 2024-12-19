package kitten.diy.api.application.port.in.command.command;

import lombok.Builder;

@Builder
public record MyProfileCommand(

        String nickName,

        String profileImgUrl,

        String userEmail
) {
}
