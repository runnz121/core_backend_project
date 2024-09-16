package kitten.diy.api.application.port.in.command.command;

import kitten.core.coredomain.board.consts.BoardType;
import kitten.diy.api.application.domain.consts.SortType;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Builder
public record BoardInfoSearchCommand(

        PageRequest pageRequest,

        SortType sortType,

        String searchTag,

        BoardType searchType
) {

    public boolean isSearchByTag() {
        return StringUtils.hasText(searchTag);
    }

    public boolean isFilterBySearchType() {
        return Objects.nonNull(searchType);
    }
}
