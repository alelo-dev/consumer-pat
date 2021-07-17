package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

/**
 * Consumer representation
 *
 * @author mcrj
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "cards")
@ToString(exclude = "cards")
public class Consumer {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    private Integer documentNumber;

    private LocalDate birthDate;

    @OneToOne(cascade = ALL)
    private Contact contact;

    @OneToOne(cascade = ALL)
    private Address address;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "consumer_id")
    private Set<Card> cards;
}
