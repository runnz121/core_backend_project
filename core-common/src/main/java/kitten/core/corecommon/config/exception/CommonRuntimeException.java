package kitten.core.corecommon.config.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class CommonRuntimeException extends RuntimeException {

    protected ErrorCode errorCode;
    protected String detailMessage;
    protected List<Object> detail = new ArrayList<>();

    public CommonRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CommonRuntimeException(ErrorCode errorCode,
                                  Objects detail) {
        this(errorCode, null, detail);
    }

    public CommonRuntimeException(ErrorCode errorCode,
                                  Object detail,
                                  Objects detail1) {
    }

    public String getDetailString() {
        StringBuilder sb = new StringBuilder();
        for (Object message : detail) {
            sb.append("\n").append(message.toString());
        }
        sb.delete(0, 1);
        return sb.toString();
    }
}
