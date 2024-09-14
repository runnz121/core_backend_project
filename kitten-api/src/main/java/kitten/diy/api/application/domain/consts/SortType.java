package kitten.diy.api.application.domain.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    RECENT("최신순"),
    LIKE("인기순"),
    VIEW("조회순")
    ;

    private final String desc;
}
