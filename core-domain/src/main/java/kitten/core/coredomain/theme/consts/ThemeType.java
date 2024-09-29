package kitten.core.coredomain.theme.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ThemeType {

    HALLOWEEN("할로윈"),
    ALL("전체")
    ;

    private final String desc;
}
