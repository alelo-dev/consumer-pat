package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Function;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"consumer", "extracts"})
@EqualsAndHashCode(exclude = {"consumer", "extracts"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "consumerId")
    private Consumer consumer;
    private String cardNumber;
    private Double balance = 0d;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Extract> extracts;

}
