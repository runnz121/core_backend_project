package kitten.diy.api.application.port.in.command;

import kitten.diy.api.application.domain.consts.SortType;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

@Builder
public record HomeInfoSearchCommand(

        PageRequest pageRequest,

        SortType sortType,

        String searchTag
) {

    public boolean isSearchByTag() {
        return StringUtils.hasText(searchTag);
    }
}
