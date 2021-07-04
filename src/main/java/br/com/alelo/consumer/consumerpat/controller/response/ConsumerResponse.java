package br.com.alelo.consumer.consumerpat.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@JsonPropertyOrder({"consumer_id", "name", "document_number", "birth_date"})
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ConsumerResponse {

    @JsonProperty("consumer_id")
    private long id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("document_number")
    private final String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private final LocalDate birthDate;
}
