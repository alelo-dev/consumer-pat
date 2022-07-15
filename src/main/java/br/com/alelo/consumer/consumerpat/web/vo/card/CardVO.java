package br.com.alelo.consumer.consumerpat.web.vo.card;

import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "number", "balance", "type" })
public class CardVO implements Serializable {

    private Long id;

    private Long number;

    private BigDecimal balance;

    private EstablishmentType type;

    public static CardVO from(Card card) {
        return CardVO.builder()
            .id(card.getId())
            .number(card.getNumber())
            .balance(card.getBalance())
            .type(card.getType())
            .build();
    }
}
