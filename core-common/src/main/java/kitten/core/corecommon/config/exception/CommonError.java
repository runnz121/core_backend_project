package kitten.core.corecommon.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorCode {

    NOT_FOUND_ROLE("COM_E_000", "유저 role를 찾을 수 없습니다."),
    NOT_FOUND_LOGIN_HISTORY("COM_E_001", "유저가 로그인한 기록을 찾을 수 없습니다."),
    NOT_FOUND_AUTH_COOKIE("COM_E_002", "인증 쿠키를 찾을 수 없습니다.")
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    CommonError(String code,
                String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
