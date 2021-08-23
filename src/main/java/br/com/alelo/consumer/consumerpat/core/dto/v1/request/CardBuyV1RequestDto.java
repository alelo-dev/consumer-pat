package br.com.alelo.consumer.consumerpat.core.dto.v1.request;

import br.com.alelo.consumer.consumerpat.core.enumeration.EstablishmentType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardBuyV1RequestDto {

    private EstablishmentType establishmentType;
    private String establishmentName;
    private String productDescription;
    private BigDecimal value;
}
