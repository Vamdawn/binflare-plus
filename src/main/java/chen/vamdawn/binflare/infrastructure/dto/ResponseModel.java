package chen.vamdawn.binflare.infrastructure.dto;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Response DTO.
 *
 * @param <T> type of field `data`
 * @author chen
 */
@Data
public class ResponseModel<T> implements Serializable {

    private int status;
    private int code;
    private String message;
    private T data;

    public ResponseModel() {
    }

    public ResponseModel(int status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * static constructor with success response.
     *
     * @param data data
     * @param <T>  type of field `data`
     * @return success response body
     */
    public static <T> ResponseModel<T> ok(T data) {
        return new ResponseModel<>(200, 0, "ok", data);
    }

    /**
     * static constructor with fail response, with 200 http status.
     *
     * @param code    response biz code
     * @param message response fail message
     * @param <T>     type of field `data`
     * @return fail response body
     */
    public static <T> ResponseModel<T> fail(Integer code, String message) {
        return fail(200, code, message);
    }

    /**
     * static constructor with fail response.
     *
     * @param code    response biz code
     * @param status  response http status
     * @param message response fail message
     * @param <T>     type of field `data`
     * @return fail response body
     */
    public static <T> ResponseModel<T> fail(int status, int code, String message) {

        Assert.isTrue(code != 0, "Fail state biz code should not be ZERO.");
        Assert.notNull(message, "Error message should not be null.");

        return new ResponseModel<>(status, code, message, null);
    }

}
