package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConsumerResponseDto extends ConsumerBaseDto {
    private Integer id;
    private ContactsResponseDto contacts;
    private AddressResponseDto address;
    private List<CardsResponseDto> cards;

    @Builder
    public ConsumerResponseDto(String name, int documentNumber, LocalDate birthDate, int id,
                               ContactsResponseDto contacts, AddressResponseDto address,
                               List<CardsResponseDto> cards) {
        super(name, documentNumber, birthDate);
        this.contacts = contacts;
        this.address = address;
        this.cards = cards;
    }
}
