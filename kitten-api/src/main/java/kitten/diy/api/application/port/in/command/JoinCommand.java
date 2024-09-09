package kitten.diy.api.application.port.in.command;

import lombok.Builder;

@Builder
public record JoinCommand(

        String email,

        Boolean isEmailConfirmation,

        String password,

        String passwordConfirmation,

        String nickName
) {
}
