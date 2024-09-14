package kitten.diy.api.application.port.in.command.command;

import lombok.Builder;

import java.util.List;

@Builder
public record JoinCommand(

        String email,

        String nickName,

        String profileImageUrl,

        String phoneNumber,

        List<Long> termsKeys
) {
}
