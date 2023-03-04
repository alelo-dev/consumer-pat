package br.com.alelo.consumer.consumerpat.application.web.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ConsumerRequest {
    private int id;
    private String name;
    @JsonProperty("document_number")
    private String documentNumber;
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private ContactRequest contact;
    private AddressRequest address;
    private List<CardRequest> cards;

}
