package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;

import java.math.BigDecimal;

public final class TransactionBuilder {

    public static TransactionDTO fullTransactionDTO() {
        TransactionDTO result = new TransactionDTO();
        result.setEstablishmentType(1);
        result.setEstablishmentName("establishmentName");
        result.setCardNumber("#1");
        result.setProductDescription("productDescription");
        result.setValue(BigDecimal.ONE);
        return result;
    }
}
