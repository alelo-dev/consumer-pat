package br.com.alelo.consumer.consumerpat.entity.consumer;


import br.com.alelo.consumer.consumerpat.entity.card.Card;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long documentNumber;
    private Date birthDate;


    @OneToMany(mappedBy = "consumer")
    private List<Card> cards;

    @OneToMany(mappedBy = "consumer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "consumer")
    private List<Contact> contacts;
}
