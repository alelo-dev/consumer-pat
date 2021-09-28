package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARD")
public class CardEntity {

    public CardEntity(CardDTO cardDTO) {
        this.id = cardDTO.getId();
        this.number = cardDTO.getCardNumber();
        this.cardBalance = cardDTO.getCardBalance();
        this.type = cardDTO.getCardType();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;
    private BigDecimal cardBalance;

    @Enumerated(EnumType.STRING)
    private CardType type;

}