package br.com.alelo.consumer.consumerpat.infra.handler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Class comments go here...
 *
 * @author André Franco
 * @version 1.0 05/12/2020
 */
@JsonPropertyOrder({"message", "code"})
@JsonInclude(value = NON_NULL)
@Builder
@Getter
public class ErrorModel {

    private static final String DEFAULT_MESSAGE = "Ops! Ocorreu um erro inesperado.";
    private String message;
    private int code;

}
