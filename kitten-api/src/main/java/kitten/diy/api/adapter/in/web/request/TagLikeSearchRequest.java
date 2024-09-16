package kitten.diy.api.adapter.in.web.request;

import kitten.diy.api.application.port.in.command.command.TagLikeSearchCommand;

public record TagLikeSearchRequest(

        String searchLikeTag,

        Integer limit
) {

    public TagLikeSearchCommand toCommand() {
        return TagLikeSearchCommand.builder()
                .searchTag(searchLikeTag)
                .limit(limit)
                .build();
    }
}
