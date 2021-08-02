package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.enums.ContactType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String contact;

    private ContactType contactType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;

    public Contact(String contact, ContactType contactType, Consumer consumer) {
        this.contact = contact;
        this.contactType = contactType;
        this.consumer = consumer;
    }

}
