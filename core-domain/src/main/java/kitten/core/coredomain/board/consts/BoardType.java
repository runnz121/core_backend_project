package kitten.core.coredomain.board.consts;

import org.springframework.util.StringUtils;

public enum BoardType {

    BEADS,
    GEM_STITCH,
    MORU
    ;

    public static BoardType of(String type) {
        if (StringUtils.hasText(type) == false) return null;
        return BoardType.valueOf(type);
    }
}
