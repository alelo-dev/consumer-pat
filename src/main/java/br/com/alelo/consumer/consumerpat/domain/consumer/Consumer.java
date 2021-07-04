package br.com.alelo.consumer.consumerpat.domain.consumer;

import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.domain.card.Card;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "consumer_id")
    private long id;

    @Column
    private String name;

    @Column(name = "document_number", unique = true)
    private String documentNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
    private Contact contact;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;


//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "consumer_id", nullable = false, insertable=false, updatable=false)
//    @OneToMany(mappedBy = "consumer", cascade = PERSIST, orphanRemoval = true)
//    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
//    @OneToMany(targetEntity=Card.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consumer_id", nullable = false, insertable=false, updatable=false)
//    @Filter(name = "onlyActiveStatusFilter")
    private List<Card> cards;



//    @Builder
    public Consumer(String name, String documentNumber, LocalDate birthDate, Contact contact, Address address, List<Card> cards) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.contact = contact;
        this.cards = cards;
    }

    public void update(final ConsumerRequest request){
        this.name = request.getName();
        this.birthDate = request.getBirthDate();
    }

//    public Consumer addCard(final Card card) {
//        if (this.cards == null) {
//            this.cards = new ArrayList<>();
//        }
//        this.cards.add(card);
//        return this;
//    }


//
//    //cards
//    int foodCardNumber;
//    double foodCardBalance;
//
//    int fuelCardNumber;
//    double fuelCardBalance;
//
//    int drugstoreNumber;
//    double drugstoreCardBalance;



}
