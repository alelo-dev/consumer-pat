package br.com.alelo.consumerpat.core.v1.response;

import br.com.alelo.consumerpat.core.enumeration.CardType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardV1ResponseDto {

    private String cardNumber;
    private BigDecimal balance;
    private CardType type;
}
