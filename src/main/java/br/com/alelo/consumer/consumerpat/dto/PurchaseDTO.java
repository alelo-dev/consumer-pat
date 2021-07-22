package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PurchaseDTO {

    private Type type;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;

    public PurchaseDTO(Type type, String establishmentName, int cardNumber, String productDescription, double value) {
        this.type = type;
        this.establishmentName = establishmentName;
        this.cardNumber = cardNumber;
        this.productDescription = productDescription;
        this.value = value;
    }
}
