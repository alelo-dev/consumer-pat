package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.domain.EstablishmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyModel {

    EstablishmentTypeEnum establishmentType;
    String establishmentName;
    Integer cardNumber;
    String productDescription;
    Double value;

}
