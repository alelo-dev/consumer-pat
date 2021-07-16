package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.TypeEstablishment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {

    private TypeEstablishment typeEstablishment;

    private String establishmentName;

    private String productDescription;

    private Long cardNumber;

    private BigDecimal value;
}
