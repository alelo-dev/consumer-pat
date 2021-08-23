package br.com.alelo.consumer.consumerpat.core.dto.v1.response;

import br.com.alelo.consumer.consumerpat.core.enumeration.CardType;
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
