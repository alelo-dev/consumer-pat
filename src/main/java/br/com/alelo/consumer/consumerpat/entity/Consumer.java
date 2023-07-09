package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer documentNumber;
    @Column
    private LocalDate birthDate;

    @OneToOne
    private Contact contact;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Card> cards;

}
