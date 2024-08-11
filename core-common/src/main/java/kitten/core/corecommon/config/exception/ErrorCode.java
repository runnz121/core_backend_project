package kitten.core.corecommon.config.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
