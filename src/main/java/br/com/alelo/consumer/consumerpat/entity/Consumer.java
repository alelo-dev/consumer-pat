package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "consumers")
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Contact contact;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    public void setContact(Contact contact) {
        if (contact == null) {
            if (this.contact != null) {
                this.contact.setConsumer(null);
            }
        } else {
            contact.setConsumer(this);
        }
        this.contact = contact;
    }

    public void addCard(Card card) {
        if (card != null) {
            if (cards == null) {
                cards = new ArrayList<>();
            }
            card.setConsumer(this);
            cards.add(card);
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setConsumer(null);
    }


}
