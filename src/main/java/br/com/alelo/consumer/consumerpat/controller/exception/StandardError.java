package br.com.alelo.consumer.consumerpat.controller.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StandardError {
    private Integer status;
    private LocalDateTime timeStamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String obs;
    private String msg;
}