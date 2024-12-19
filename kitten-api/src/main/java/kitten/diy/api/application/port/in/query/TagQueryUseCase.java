package kitten.diy.api.application.port.in.query;

import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;

import java.util.List;

public interface TagQueryUseCase {

    List<String> getLikeTags(TagLikeSearchCommand command);
}
