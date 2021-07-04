package br.com.alelo.consumer.consumerpat.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@AllArgsConstructor
public class ConsumerRequest {

    @ApiModelProperty(example = "João Silva", required = true)
    @JsonProperty("name")
    private final String name;

    @ApiModelProperty(example = "77733322200", required = true)
    @JsonProperty("document_number")
    private final String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("birth_date")
    private final LocalDate birthDate;

    @JsonProperty("address")
    private AddressRequest address;

    @JsonProperty("contact")
    private ContactRequest contact;

    @JsonProperty("cards")
    private List<CardRequest> cards;
}
