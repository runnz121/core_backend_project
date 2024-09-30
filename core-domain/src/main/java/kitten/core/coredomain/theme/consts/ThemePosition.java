package kitten.core.coredomain.theme.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ThemePosition {

    ALL("전체"),
    NECK("목"),
    TAIL("꼬리"),
    HEAD("머리")
    ;

    private final String desc;
}
