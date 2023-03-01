package br.com.alelo.consumer.consumerpat.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Schema
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiError {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "2023-02-25T08:00:00.0000000Z")
    private OffsetDateTime timestamp;

    @Schema(example = "Invalid input data")
    private String title;

    @Schema(example = "/consumers")
    private String uri;

    @Schema(example = "Invalid input data.")
    private String userMessage;

    @Schema(description = "Invalid input data fields.")
    private List<RestApiErrorDetail> details;

    @Schema
    @Builder
    @Getter
    static class RestApiErrorDetail {

        @Schema(example = "documentNumber")
        private String name;

        @Schema(example = "Document number must be valid.")
        private String userMessage;
    }
}
