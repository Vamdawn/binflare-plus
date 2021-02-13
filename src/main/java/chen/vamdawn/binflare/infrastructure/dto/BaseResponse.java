package chen.vamdawn.binflare.infrastructure.dto;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Base response DTO
 *
 * @param <T> type of field `data`
 * @author chen
 */
@Data
public class BaseResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * static constructor with success response.
     *
     * @param data data
     * @param <T>  type of field `data`
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(0);
        response.setMessage("ok");
        response.setData(data);
        return response;
    }

    /**
     * static constructor with fail response.
     *
     * @param code    response biz code
     * @param message response fail message
     * @param <T>     type of field `data`
     * @return BaseResponse
     */
    public static <T> BaseResponse<T> fail(Integer code, String message) {
        Assert.notNull(code, "Biz code should not be null.");
        Assert.isTrue(code != 0, "Fail state biz code should not be ZERO.");
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
