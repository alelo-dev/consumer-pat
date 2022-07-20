package br.com.alelo.consumer.consumerpat.controller.model;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuyRequest {

    private final EstablishmentType establishmentType;
    private final int cardNumber;
    private final String productDescription;
    private final double value;

    public Extract toExtract() {
        return Extract.builder()
            .establishmentNameId(establishmentType.getCode())
            .establishmentName(establishmentType.name())
            .productDescription(productDescription)
            .dateBuy(LocalDateTime.now())
            .cardNumber(cardNumber)
            .value(value)
            .build();
    }
}
