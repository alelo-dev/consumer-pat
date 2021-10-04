package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    private EstablishmentType establishmentType;
    private String establishmentName;
    private String cardNumber;
    private String productDescription;
    private Double value;
}
