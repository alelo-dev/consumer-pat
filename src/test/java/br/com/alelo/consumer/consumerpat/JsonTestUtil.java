package br.com.alelo.consumer.consumerpat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestUtil {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
