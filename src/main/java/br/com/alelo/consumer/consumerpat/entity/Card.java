package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="TB_CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer cardNumber;
    private Double cardBalance;

    @Enumerated(EnumType.ORDINAL)
    private EstablishmentType establishmentType;

    @ManyToOne
    @JoinColumn(name = "CONSUMER_ID")
    private Consumer consumer;

}
