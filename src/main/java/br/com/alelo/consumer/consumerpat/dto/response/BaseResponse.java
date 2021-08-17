package br.com.alelo.consumer.consumerpat.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class BaseResponse<T> {
    private List<Error> errors;

    public static BaseResponse notOk(int code, String message) {
        return new BaseResponse<>(Collections.singletonList(Error.error(code, message)));
    }

    public static BaseResponse notOk(int code, String message, Map<String, Object> details) {
        return new BaseResponse<>(Collections.singletonList(Error.error(code, message, details)));
    }

    public static BaseResponse notOk(List<Error> errors) {
        return new BaseResponse<>(errors);
    }

    @AllArgsConstructor
    @Getter
    public enum ErrorType {
        ERROR("E"),
        WARNING("W"),
        INFORMATION("I");

        @JsonValue
        private String value;
        }

    @AllArgsConstructor
    @Getter
    public static class Error {
        private int code;
        private ErrorType type;
        private String description;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, Object> details;

        public static Error error(int code, String description) {
            return new Error(code, ErrorType.ERROR, description, null);
        }

        public static Error error(int code, String description, Map<String, Object> details) {
            return new Error(code, ErrorType.ERROR, description, details);
        }

    }

}
