package kitten.core.corecommon.config.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonRuntimeException.class)
    public ProblemDetail hntRuntimeException(CommonRuntimeException failed) {
        HttpStatusCode httpStatusCode = failed.getErrorCode().getHttpStatus();
        String detailMessage = failed.getDetailMessage();
        return ProblemDetail.forStatusAndDetail(httpStatusCode, detailMessage);
    }
}
