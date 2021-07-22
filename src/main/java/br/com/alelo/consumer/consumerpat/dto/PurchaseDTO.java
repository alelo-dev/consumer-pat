package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EntityManager;

@Getter
@AllArgsConstructor
public class PurchaseDTO {

    private Type type;
    private String establishmentName;
    private int cardNumber;
    private String productDescription;
    private double value;

    public PurchaseDTO(int cardNumber, double value) {
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Purchase toModel(EntityManager manager) {
        return new Purchase(type, establishmentName, cardNumber, productDescription, value);
    }

}
