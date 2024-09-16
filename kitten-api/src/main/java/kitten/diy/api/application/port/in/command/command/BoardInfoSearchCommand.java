package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.board.consts.BoardType;
import kitten.diy.api.application.domain.consts.SortType;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Builder
public record BoardInfoSearchCommand(

        PageRequest pageRequest,

        SortType sortType,

        List<String> searchTags,

        BoardType searchType
) {

    public boolean isNotSearchByTag() {
        return CollectionUtils.isEmpty(searchTags);
    }

    public boolean isFilterBySearchType() {
        return Objects.nonNull(searchType);
    }
}
