package br.com.alelo.consumer.consumerpat.exceptions;

import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Fault {

    @JsonProperty("code")
    private ErrorCodeEnum code = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("details")
    private String details = null;

    public Fault code(final ErrorCodeEnum code) {
        this.code = code;
        return this;
    }


}
