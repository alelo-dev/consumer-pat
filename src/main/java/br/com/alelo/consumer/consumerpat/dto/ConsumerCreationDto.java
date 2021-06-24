package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConsumerCreationDto extends ConsumerBaseDto {
    private List<CardsCreationDto> cards;
    private ContactsCreationDto contacts;
    private AddressCreationDto address;

    @Builder
    public ConsumerCreationDto(String name, int documentNumber, LocalDate birthDate, ContactsCreationDto contacts, AddressCreationDto address, List<CardsCreationDto> cards) {
        super(name, documentNumber, birthDate);
        this.contacts = contacts;
        this.address = address;
        this.cards = cards;
    }
}
