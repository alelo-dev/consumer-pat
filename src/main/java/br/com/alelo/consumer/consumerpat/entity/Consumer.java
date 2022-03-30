package br.com.alelo.consumer.consumerpat.entity;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "consumer")
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long documentNumber;
    private LocalDate birthDate;

    @OneToOne(mappedBy="consumer")
    private Contact contact;

    @OneToOne(mappedBy="consumer")
    private Address address;

    @OneToMany(mappedBy = "consumer")
    private List<Card> cards;

}
