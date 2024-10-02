package kitten.core.coredomain.theme.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ThemePosition {

    ALL("모든 부위"),
    NECK("목"),
    HEAD("머리"),
    ORNAMENT("장식품"),
    MOUTH("입"),
    EYE("눈"),
    WING("날개")
    ;

    private final String desc;
}
