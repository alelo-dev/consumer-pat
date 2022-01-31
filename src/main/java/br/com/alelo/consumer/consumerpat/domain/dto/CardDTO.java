package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO extends BaseDTO{
    private static final long serialVersionUID = 1L;

    private Integer cardNumber;
    private BigDecimal balanceValue;
    private CardType cardType;
    private Consumer consumer;

}
