package br.com.alelo.consumer.consumerpat.domain.service;

import java.math.BigDecimal;

public interface Strategy {

    public BigDecimal creditValue(BigDecimal balance, BigDecimal value);

    public BigDecimal applyCashback(BigDecimal balance, BigDecimal value);
}
