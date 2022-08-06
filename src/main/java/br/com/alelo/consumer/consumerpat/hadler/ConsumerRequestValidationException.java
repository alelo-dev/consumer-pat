package br.com.alelo.consumer.consumerpat.hadler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class ConsumerRequestValidationException {

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Fields> fields;

    @AllArgsConstructor
    @Getter
    public static class Fields {
        private String name;
        private String message;
    }
}
