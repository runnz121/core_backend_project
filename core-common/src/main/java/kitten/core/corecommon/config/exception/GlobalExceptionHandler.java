package kitten.core.corecommon.config.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kitten.core.corecommon.config.exception.ProblemDetailFactory.ofProblemDetail;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonRuntimeException.class)
    public ProblemDetail hntRuntimeException(CommonRuntimeException failed) {
        ProblemDetail problemDetail = ofProblemDetail(failed, failed.getErrorCode(), null, null);
        return addMoreProperties(problemDetail, failed);
    }

    private ProblemDetail addMoreProperties(ProblemDetail problemDetail,
                                            CommonRuntimeException failed) {
        if (!CollectionUtils.isEmpty(failed.getDetail())) {
            problemDetail.setProperty("more", failed.getDetailString());
        }
        return problemDetail;
    }
}
