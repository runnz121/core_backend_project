package kitten.diy.api.adapter.out.error;

import kitten.core.corecommon.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ThemeError implements ErrorCode {

    THEME_NOT_FOUND("THE_E_001", "테마 정보가 없습니다")
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ThemeError(String code,
                   String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
