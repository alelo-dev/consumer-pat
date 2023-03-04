package br.com.alelo.consumer.consumerpat.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Consumer {
    private int id;
    private String name;
    @JsonProperty("document_number")
    private String documentNumber;
    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private Contact contact;
    private Address address;
    private List<Card> cards;

    public void removeCards() {
        cards = null;
    }
}
