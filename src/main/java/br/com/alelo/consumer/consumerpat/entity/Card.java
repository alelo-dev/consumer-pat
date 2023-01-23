package br.com.alelo.consumer.consumerpat.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long number;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private CardType type;
}
