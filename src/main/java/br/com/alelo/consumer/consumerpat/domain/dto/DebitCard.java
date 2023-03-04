package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebitCard {
    private int consumerId;
    private EstablishmentType establishmentType;
    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private double value;

}
