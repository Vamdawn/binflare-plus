package chen.vamdawn.binflare.infrastructure.exception;


/**
 * Business Exception (with codes response)
 *
 * @author chen
 */
public class BizException extends RuntimeException {

    private final int httpCode;
    private final int bizCode;

    public BizException(int httpCode, int bizCode, String message) {
        super(message);
        this.httpCode = httpCode;
        this.bizCode = bizCode;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public int getBizCode() {
        return bizCode;
    }

    @Override
    public String toString() {
        return "BizException(httpCode=" + getHttpCode() + ", bizCode=" + getBizCode() + ", message=" + getMessage() + ")";
    }
}
