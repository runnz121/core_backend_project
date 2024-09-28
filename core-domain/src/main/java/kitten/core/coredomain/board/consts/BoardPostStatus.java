package kitten.core.coredomain.board.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardPostStatus {

    TEMP_SAVE("임시 저장"),
    POST("게시글")
    ;

    private final String desc;
}
