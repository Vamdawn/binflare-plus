package chen.vamdawn.binflare.infrastructure.exception;


/**
 * Business Exception (with codes response)
 *
 * @author chen
 */
public class BizException extends RuntimeException {

    private final int status;
    private final int code;

    public BizException(int status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BizException(status=" + getStatus() + ", code=" + getCode() + ", message=" + getMessage() + ")";
    }
}
