package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.TypeEstablishment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyDTO {

    private TypeEstablishment typeEstablishment;

    private String establishmentName;

    private String productDescription;

    private Long cardNumber;

    private BigDecimal value;
}
