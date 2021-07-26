package br.com.alelo.consumer.consumerpat.model.dto.consumer;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 12:21
 */

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ConsumerDTO {

    private Integer id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    List<ConsumerAleloCardDTO> cards;
    List<ConsumerContactDTO> contacts;
    List<ConsumerAddressDTO> address;
    private LocalDateTime createdAt;

    public ConsumerDTO(Consumer consumer, List<ConsumerAleloCardDTO> cards, List<ConsumerContactDTO> contacts, List<ConsumerAddressDTO> address) {
        this.id = Objects.requireNonNull(consumer).getId();
        this.name = Objects.requireNonNull(consumer).getName();
        this.documentNumber = Objects.requireNonNull(consumer).getDocumentNumber();
        this.birthDate = Objects.requireNonNull(consumer).getBirthDate();
        this.createdAt = Objects.requireNonNull(consumer).getCreatedAt();
        this.cards = cards;
        this.contacts = contacts;
        this.address = address;
    }
}
