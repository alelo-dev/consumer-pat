package br.com.alelo.consumer.consumerpat.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class CardBuyV1RequestDto {

    private Integer establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private Double value;
}
