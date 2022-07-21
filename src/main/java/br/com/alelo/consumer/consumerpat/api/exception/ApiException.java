package br.com.alelo.consumer.consumerpat.api.exception;

import br.com.alelo.consumer.consumerpat.domain.exception.InvalidRequestException;
import br.com.alelo.consumer.consumerpat.domain.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

/**
 * Classe que modela exceções lançadas pela API.
 */
@Data
public class ApiException {

    @NonNull
    @JsonIgnore
    private Exception exception;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;
    private int code;
    private String message;
    private String debugMessage;

    public ApiException(Exception exception, LocalDateTime timestamp) {
        this.exception = exception;
        this.timestamp = timestamp;
        if (exception instanceof InvalidRequestException) {
            this.status = BAD_REQUEST;
        } else if (exception instanceof ResourceNotFoundException) {
            this.status = NOT_FOUND;
        } else {
            this.status = INTERNAL_SERVER_ERROR;
        }
        this.code = status.value();
        this.message = status.getReasonPhrase();
        this.setDebugMessage(exception.getLocalizedMessage());
    }

}
