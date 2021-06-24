package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConsumerUpdateDto extends ConsumerBaseDto{
    private Integer id;
    private ContactsUpdateDto contacts;
    private AddressUpdateDto address;
    private CardsUpdateDto cards;

    @Builder
    public ConsumerUpdateDto(String name, int documentNumber, LocalDate birthDate, int id,
                             ContactsUpdateDto contacts, AddressUpdateDto address,
                             CardsUpdateDto cards) {
        super(name, documentNumber, birthDate);
        this.contacts = contacts;
        this.address = address;
        this.cards = cards;
    }
}
