package br.com.alelo.consumer.consumerpat.entity;


import br.com.alelo.consumer.consumerpat.dto.ConsumerInDTO;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    //cards
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Card> cards;

    public Consumer() {
    }

    public Consumer(ConsumerInDTO dto) {

        this.name = dto.getName();
        this.birthDate = dto.getBirthDate();
        this.documentNumber = dto.getDocumentNumber();
        this.email = dto.getEmail();
        this.phone = new Phone(dto.getPhone());
        this.address = new Address(dto.getAddress());
        this.cards = (cards == null) ? new HashSet<>() : cards;
        dto.getCards().stream().forEach(c -> {
            if (c.getCardType().compareTo(CardType.DRUGSTORE_CARD) == 0)
                this.cards.add(new DrugstoreCard(c));
            if (c.getCardType().compareTo(CardType.FOOD_CARD) == 0)
                this.cards.add(new FoodCard(c));
            if (c.getCardType().compareTo(CardType.FUEL_CARD) == 0)
                this.cards.add(new FuelCard(c));
        });
//        this.cards = new Card(dto.getCards());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return documentNumber == consumer.documentNumber
                && Objects.equals(id, consumer.id)
                && Objects.equals(name, consumer.name)
                && Objects.equals(birthDate, consumer.birthDate)
                && Objects.equals(email, consumer.email)
                && Objects.equals(address, consumer.address);
    }
}
