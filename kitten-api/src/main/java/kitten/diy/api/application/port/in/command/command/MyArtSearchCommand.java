package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.user.consts.MyArtType;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;

@Builder
public record MyArtSearchCommand(

        PageRequest pageRequest,

        MyArtType searchType
) {
}
