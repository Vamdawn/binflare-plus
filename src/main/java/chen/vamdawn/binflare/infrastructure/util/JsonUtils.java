package chen.vamdawn.binflare.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON operation utilities based on Jasckson.
 *
 * @author chen
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
        throw new UnsupportedOperationException("utility class should not be instantiated.");
    }

    /**
     * Java Object => Json String
     *
     * @param object java object
     * @return json string
     */
    public static String toJsonString(Object object) {
        String result = null;
        try {
            result = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException ignored) {
        }
        return result;
    }
}
