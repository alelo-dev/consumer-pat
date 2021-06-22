package br.com.alelo.consumer.consumerpat.parameter;

import br.com.alelo.consumer.consumerpat.entity.enumeration.PurchaseType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseParameter {

    private int consumerId;
    private PurchaseType type;

    private String establishmentName;
    private String productDescription;
    private BigDecimal productValue;

    private String cardNumber;
}
