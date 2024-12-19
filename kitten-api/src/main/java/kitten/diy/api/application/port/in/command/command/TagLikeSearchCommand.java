package kitten.diy.api.application.port.in.command.command;

import lombok.Builder;

@Builder
public record TagLikeSearchCommand(

        String searchTag,

        Integer limit
) {
}
