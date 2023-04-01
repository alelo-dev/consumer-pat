package br.com.alelo.consumer.consumerpat.response;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@NoArgsConstructor
public class SettlingResponse {
    CardType cardType;
    private Integer establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private BigDecimal value;
}
