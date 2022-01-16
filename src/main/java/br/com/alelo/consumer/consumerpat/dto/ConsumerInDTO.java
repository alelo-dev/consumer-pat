package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumerInDTO implements Serializable {

    private String name;

    private String documentNumber;

    private LocalDate birthDate;

    private String email;

    private PhoneDTO phone;

    private AddressDTO address;

    private Set<CardDTO> cards;

    public ConsumerInDTO(Consumer consumer) {

        this.name = consumer.getName();
        this.documentNumber = consumer.getDocumentNumber();
        this.birthDate = consumer.getBirthDate();
        this.email = consumer.getEmail();
        this.phone = new PhoneDTO(consumer.getPhone());
        this.address = new AddressDTO(consumer.getAddress());
        this.cards = (cards == null) ? new HashSet<>() : cards;
        consumer.getCards().stream().forEach(c -> this.cards.add(new CardDTO(c)));
    }
}
