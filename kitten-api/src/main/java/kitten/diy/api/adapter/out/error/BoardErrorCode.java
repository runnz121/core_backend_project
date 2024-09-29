package kitten.diy.api.adapter.out.error;

import kitten.core.corecommon.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    BOARD_NOT_FOUND("BOR_E_001", "게시판 정보가 없습니다"),
    BOARD_ITEM_NOT_FOUND("BOR_E_002", "게시글 아이템 정보가 없습니다")
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    BoardErrorCode(String code,
                String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
