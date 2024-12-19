package kitten.core.corecommon.config.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.util.StringUtils;

import java.net.URI;

public abstract class ProblemDetailFactory {

    private static ProblemDetail ofProblemDetail(Exception failed,
                                                 HttpStatusCode httpStatus,
                                                 String message,
                                                 String errorCode,
                                                 String traceId,
                                                 URI uri
    ) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setProperty("traceId", traceId);
        problemDetail.setInstance(uri);
        if (StringUtils.hasText(errorCode)) {
            problemDetail.setProperty("errorCode", errorCode);
        }
        return problemDetail;
    }

    public static ProblemDetail ofProblemDetail(Exception failed,
                                                ErrorCode errorCode,
                                                String traceId,
                                                URI uri) {
        return ofProblemDetail(
                failed,
                errorCode.getHttpStatus(),
                errorCode.getMessage(),
                errorCode.getCode(),
                traceId,
                uri);
    }
}
