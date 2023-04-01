package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.entity.CardType;
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
public class SettlingRequest {
    CardType cardType;
    private Integer establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private BigDecimal value;
}
