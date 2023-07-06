package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Contact {
    //contacts
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    Integer id;

    int mobilePhoneNumber;
    int residencePhoneNumber;
    int phoneNumber;
    String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    @JsonBackReference
    private Consumer consumer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return this.mobilePhoneNumber == contact.mobilePhoneNumber
                && this.residencePhoneNumber == contact.residencePhoneNumber
                && this.phoneNumber == contact.phoneNumber
                && Objects.equals(this.email, contact.email)
                && Objects.equals(this.id, contact.id);
    }

}
