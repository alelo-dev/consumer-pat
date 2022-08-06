package br.com.alelo.consumer.consumerpat.response;

import br.com.alelo.consumer.consumerpat.domain.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {

    private Long id;

    private String cardNumber;

    private BigDecimal cardBalance;

    private CardType cardtype;

    private boolean active;
}
