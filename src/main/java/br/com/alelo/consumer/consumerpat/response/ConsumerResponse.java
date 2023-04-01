package br.com.alelo.consumer.consumerpat.response;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@NoArgsConstructor
public class ConsumerResponse {
    private Integer id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private Contact contact;
    private Address address;
    private Set<Card> cards;

}
