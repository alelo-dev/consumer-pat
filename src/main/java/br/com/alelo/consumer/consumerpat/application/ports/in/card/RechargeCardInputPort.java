package br.com.alelo.consumer.consumerpat.application.ports.in.card;

import java.math.BigDecimal;

public interface RechargeCardInputPort {
    void recharge(String cardNumber, BigDecimal amount);
}
