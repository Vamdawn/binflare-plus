package chen.vamdawn.binflare.infrastructure.logging;

import chen.vamdawn.binflare.infrastructure.util.JsonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.Instant;

/**
 * HTTP Request record for logging.
 *
 * @author chen
 */
public class LoggingRequestRecord implements Serializable {

    @JsonProperty("method")
    private String method;

    @JsonProperty("path")
    private String path;

    @JsonProperty("http_status")
    private int httpStatus;

    @JsonProperty("request_time")
    private Instant requestTime;

    @JsonProperty("request_headers")
    private HttpHeaders requestHeaders;

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("request_query")
    private String requestQuery;

    @JsonProperty("request_body")
    private Object requestBody;

    @JsonProperty("response_time")
    private Instant responseTime;

    @JsonProperty("response_headers")
    private HttpHeaders responseHeaders;

    @JsonProperty("response_body")
    private Object responseBody;

    @JsonProperty("duration")
    private String duration;

    private LoggingRequestRecord() {
    }

    /**
     * Generate new instance and set request time
     *
     * @return LoggingRequestRecord Instance
     */
    public static LoggingRequestRecord init() {
        LoggingRequestRecord instance = new LoggingRequestRecord();
        instance.requestTime = Instant.now();
        return instance;
    }

    /**
     * Set response time and request duration (in ms)
     *
     * @return this
     */
    public LoggingRequestRecord commit() {
        this.responseTime = Instant.now();
        this.duration = String.format("%dms", this.responseTime.toEpochMilli() - this.requestTime.toEpochMilli());
        return this;
    }

    /**
     * parse HttpServletRequest, set method, path, clientIp, requestQuery, requestHeaders and requestBody.
     *
     * @param requestWrapper HttpServletRequest
     * @return this
     */
    public LoggingRequestRecord parse(ContentCachingRequestWrapper requestWrapper) {
        this.method = requestWrapper.getMethod();
        this.path = requestWrapper.getServletPath();
        this.clientIp = requestWrapper.getRemoteAddr();
        this.requestQuery = requestWrapper.getQueryString();
        this.requestHeaders = new ServletServerHttpRequest((HttpServletRequest) requestWrapper.getRequest()).getHeaders();
        byte[] buffer = requestWrapper.getContentAsByteArray();
        this.requestBody = tryConvertJson(buffer, requestWrapper.getCharacterEncoding());
        return this;
    }

    /**
     * parse HttpServletResponse, set httpStatus, responseHeaders and responseBody.
     *
     * @param responseWrapper HttpServletResponse
     * @return this
     */
    public LoggingRequestRecord parse(ContentCachingResponseWrapper responseWrapper) {
        this.httpStatus = responseWrapper.getStatus();
        this.responseHeaders = new ServletServerHttpResponse((HttpServletResponse) responseWrapper.getResponse()).getHeaders();
        byte[] buffer = responseWrapper.getContentAsByteArray();
        this.responseBody = tryConvertJson(buffer, responseWrapper.getCharacterEncoding());
        return this;
    }

    /**
     * Serialize LoggingRequestRecord to json.
     *
     * @return json string
     */
    public String json() {
        return JsonUtils.serialize(this);
    }

    private Object tryConvertJson(byte[] buffer, String characterEncoding) {
        String body = null;
        if (buffer.length > 0) {
            try {
                body = new String(buffer, characterEncoding);
                if (JSONValidator.from(body).validate()) {
                    return JSON.parse(body);
                }
            } catch (UnsupportedEncodingException ignored) {
            }
        }
        return body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public Instant getRequestTime() {
        return requestTime;
    }

    public HttpHeaders getRequestHeaders() {
        return requestHeaders;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getRequestQuery() {
        return requestQuery;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public Instant getResponseTime() {
        return responseTime;
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public String getDuration() {
        return duration;
    }
}
