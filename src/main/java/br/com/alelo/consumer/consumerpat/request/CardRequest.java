package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private Integer id;
    private BigDecimal balance;
    private String number;
    private CardType cardType;
    private Consumer consumer;
}
