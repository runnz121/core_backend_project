package kitten.diy.api.adapter.out.persistence.query;

import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;

import java.util.List;

public interface ItemQueryFetch {

    List<String> getPartsTagLikes(TagLikeSearchCommand command);
}
