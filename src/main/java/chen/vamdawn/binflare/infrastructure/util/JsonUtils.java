package chen.vamdawn.binflare.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JSON operation utilities based on Jasckson.
 *
 * @author chen
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private JsonUtils() {
        throw new UnsupportedOperationException("utility class should not be instantiated.");
    }

    /**
     * Java Object => Json String
     *
     * @param object java object
     * @return json string
     */
    public static String serialize(Object object) {
        String value = null;
        try {
            value = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException ignored) {
        }
        return value;
    }

    /**
     * Json String => Java Object
     *
     * @param value json string
     * @param clazz java class of object
     * @param <T>   parameterized type of java object
     * @return java object
     */
    public static <T> T deserialize(String value, Class<T> clazz) {
        T object = null;
        try {
            object = MAPPER.readValue(value, clazz);
        } catch (JsonProcessingException ignored) {
        }
        return object;
    }
}
