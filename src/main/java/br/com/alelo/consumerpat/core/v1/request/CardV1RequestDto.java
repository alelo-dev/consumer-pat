package br.com.alelo.consumerpat.core.v1.request;

import br.com.alelo.consumerpat.core.enumeration.CardType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Getter
public class CardV1RequestDto {

    private String card;
    private BigDecimal balance;
    private CardType type;
}
