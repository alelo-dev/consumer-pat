package br.com.alelo.consumer.consumerpat.consumer.domain;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consumerId;

    private String name;

    private int documentNumber;

    private LocalDate birthDate;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumer", cascade = CascadeType.ALL)
    private Set<Contact> contacts;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consumer", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "holder", cascade = CascadeType.ALL)
    private Set<Card> cards;

    public boolean addCard(Card card) {
        if (this.cards == null)
            this.cards = new HashSet<>();

        return this.cards.add(card);
    }

    public boolean removeCard(Card card) {
        if (this.cards == null)
            return true;

        return this.cards.remove(card);
    }

    public boolean addContact(Contact contact) {
        if (this.contacts == null)
            this.contacts = new HashSet<>();

        return this.contacts.add(contact);
    }

    public boolean removeContact(Contact contact) {
        if (this.cards == null)
            return true;

        return this.contacts.remove(contact);
    }

    public boolean addAddress(Address address) {
        if (this.addresses == null)
            this.addresses = new HashSet<>();

        return this.addresses.add(address);
    }

    public boolean removeAddress(Address address) {
        if (this.addresses == null)
            return true;

        return this.addresses.remove(address);
    }
}
