package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    ContactType type;
    String value;
    @ManyToOne
    @JsonBackReference
    private Consumer consumer;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    protected Contact(){

    }
}
