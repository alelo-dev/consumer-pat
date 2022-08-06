package br.com.alelo.consumer.consumerpat.hadler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConsumerApiResponseException {

    private Integer httpStatusCode;
    private HttpStatus httpStatus;
    private String errorMessage;
    private String errorCode;
    private String detailMessage;

}
