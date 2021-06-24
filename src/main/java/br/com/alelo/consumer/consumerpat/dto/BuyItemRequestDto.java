package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyItemRequestDto {
    private EstablishmentType establishmentType;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;
}
