package kitten.diy.api.adapter.out.error;


import kitten.core.corecommon.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PartsErrorCode implements ErrorCode {

    NOT_REPRESENTATIVE_PARTS_EXISTS("PAR_E_001", "대표 퍄츠 정보가 없습니다.")
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    PartsErrorCode(String code,
                   String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
