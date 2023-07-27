package br.com.alelo.consumer.consumerpat.application.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts an object to JSON string.
     *
     * @param object the object to be converted to JSON
     * @return the JSON representation of the object as a string
     * @throws JsonProcessingException if there is an error processing the JSON
     */
    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    /**
     * Converts a JSON string to an object of the specified class.
     *
     * @param json   the JSON string to be converted to an object
     * @param clazz  the target class of the object
     * @param <T>    the type of the object
     * @return the deserialized object
     * @throws JsonProcessingException if there is an error processing the JSON
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}

