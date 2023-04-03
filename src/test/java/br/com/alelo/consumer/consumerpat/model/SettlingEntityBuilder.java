package br.com.alelo.consumer.consumerpat.model;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.request.SettlingRequest;

import java.math.BigDecimal;

public class SettlingEntityBuilder {

    public static SettlingRequest settlingRequestBuilder() {
        SettlingRequest settling = new SettlingRequest();
        settling.setCardNumber("12736123913719371");
        settling.setEstablishmentName("MERCADO");
        settling.setValue(BigDecimal.valueOf(350));
        settling.setCardType(CardType.FOOD);
        settling.setProductDescription("Refri");
        settling.setEstablishmentType(1);

        return settling;
    }
}
