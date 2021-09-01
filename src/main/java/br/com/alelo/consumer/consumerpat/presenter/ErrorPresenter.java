package br.com.alelo.consumer.consumerpat.presenter;

import br.com.alelo.consumer.consumerpat.utils.RequestUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

/**
 * @author Guilherme de Castro
 * @created 01/09/2021 - 14:09
 */

@Data
public class ErrorPresenter {
    @ApiModelProperty(required = true, example = "1996-10-06T21:00:13.086Z", value = "The timestamp where the error occurred.")
    private String timestamp;

    @ApiModelProperty(example = "400", required = true, value = "The HTTP status. Must be the same status returned as request response.")

    private Integer status;

    @ApiModelProperty(example = "Bad Request", required = true, value = "The description of the HTTP status.")
    private String error;

    @ApiModelProperty(required = true, example = "/companies")
    private String path;

    @ApiModelProperty(required = true, example = "9aa321b3-96ac-4f26-b3cf-6390cb905a06")
    private String transactionId;

    @JsonProperty
    @ApiModelProperty
    private Set<ErrorDetail> errors;

    @Builder
    public ErrorPresenter(final HttpStatus status, final Set<ErrorDetail> errors) {
        super();
        if (!Objects.isNull(status)) {
            this.error = status.name();
            this.status = status.value();
        }
        this.timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        this.path = RequestUtils.getContextPath();
        this.errors = errors;
    }

    public void updatePath() {
        this.path = RequestUtils.getContextPath();
    }
}
