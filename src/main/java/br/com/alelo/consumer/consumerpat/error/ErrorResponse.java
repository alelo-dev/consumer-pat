package br.com.alelo.consumer.consumerpat.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String field;

    private String error;

}
