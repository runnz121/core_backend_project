package kitten.diy.api.adapter.out.consts;

import kitten.core.corecommon.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NICK_NAME_ALREADY_EXISTS("USER_E_001", "이미 존재하는 닉네임입니다.");

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    UserErrorCode(String code,
                   String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
