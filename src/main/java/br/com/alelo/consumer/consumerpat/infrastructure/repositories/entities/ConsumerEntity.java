package br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities;

import br.com.alelo.consumer.consumerpat.domain.entities.Card;
import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "consumer")
@NoArgsConstructor
public class ConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consumer_id")
    private int id;
    private String name;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactEntity contact;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL)
    private List<CardEntity> cards;

    public Consumer toModel() {
        Consumer consumer = new Consumer();

        BeanUtils.copyProperties(this, consumer);
        consumer.setContact(contact.toModel());
        consumer.setAddress(address.toModel());

        if (cards != null) {
            List<Card> cardsToModel = new ArrayList<>();
            for (CardEntity cardEntity : cards) {
                if (cardEntity != null) {
                    cardsToModel.add(cardEntity.toModel());
                }
            }
            consumer.setCards(cardsToModel);
        }
        return consumer;
    }
}
