package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApiErrors {

    @JsonFormat(pattern = Constants.DATETIME_FORMAT_PATTERN)
    private LocalDateTime timestamp;

    private Object errors;

    private String details;

}
