package br.com.alelo.consumerpat.core.v1.request;

import br.com.alelo.consumerpat.core.enumeration.EstablishmentType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Getter
public class CardBuyV1RequestDto {

    private EstablishmentType establishmentType;
    private String establishmentName;
    private String productDescription;
    private BigDecimal value;
}
