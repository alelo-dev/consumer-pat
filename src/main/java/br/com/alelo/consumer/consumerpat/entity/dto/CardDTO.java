package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO implements Serializable {

    private Integer number;
    private BigDecimal balance;
    private CardType cardType;

}
