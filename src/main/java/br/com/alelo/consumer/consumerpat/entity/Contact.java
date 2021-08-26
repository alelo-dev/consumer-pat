package br.com.alelo.consumer.consumerpat.entity;

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
    private Consumer consumer;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    protected Contact(){

    }
}
