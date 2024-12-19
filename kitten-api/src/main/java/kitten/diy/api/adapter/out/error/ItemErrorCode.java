package kitten.diy.api.adapter.out.error;

import kitten.core.corecommon.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ItemErrorCode implements ErrorCode {

    NOT_FOUND_MORU("MORU_E_001", "모루 인형 정보를 찾을 수 없습니다."),
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ItemErrorCode(String code,
                   String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
