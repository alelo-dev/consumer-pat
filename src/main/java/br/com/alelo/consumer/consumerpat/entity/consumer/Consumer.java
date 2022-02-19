package br.com.alelo.consumer.consumerpat.entity.consumer;


import br.com.alelo.consumer.consumerpat.entity.card.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Long documentNumber;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Card> cards;

    @OneToMany(mappedBy = "consumer",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Address> addresses;

    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Contact> contacts;
}
