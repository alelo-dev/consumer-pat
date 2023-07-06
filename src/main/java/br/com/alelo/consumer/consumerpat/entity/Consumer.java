package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    Card card;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    Contact contact;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return this.documentNumber == consumer.documentNumber
            && Objects.equals(this.id, consumer.id)
            && Objects.equals(this.name, consumer.name)
            && Objects.equals(this.birthDate, consumer.birthDate);
    }

}
