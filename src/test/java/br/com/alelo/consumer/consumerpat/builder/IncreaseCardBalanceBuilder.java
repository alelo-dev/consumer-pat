package br.com.alelo.consumer.consumerpat.builder;

import br.com.alelo.consumer.consumerpat.dto.IncreaseCardBalanceDTO;

import java.math.BigDecimal;

public final class IncreaseCardBalanceBuilder {

    public static IncreaseCardBalanceDTO fullIncreaseCardBalanceDTO() {
        IncreaseCardBalanceDTO request = new IncreaseCardBalanceDTO();
        request.setNumber("#1");
        request.setValue(BigDecimal.ONE);
        return request;
    }
}
