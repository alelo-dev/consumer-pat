package br.com.alelo.consumer.consumerpat.api.dto;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Classe que modela os dados do saldo do consumidor.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class CardBalanceDto extends Dto {
    private BigInteger cardNumber;
    private BigDecimal value;
    private CardType cardType;
}
