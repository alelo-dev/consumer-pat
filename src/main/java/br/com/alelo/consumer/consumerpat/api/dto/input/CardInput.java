package br.com.alelo.consumer.consumerpat.api.dto.input;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.model.CardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardInput {

    private int number;
    private BigDecimal balance;
    private CardType type;
}
