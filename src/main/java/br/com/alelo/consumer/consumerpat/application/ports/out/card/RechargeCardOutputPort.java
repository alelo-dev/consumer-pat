package br.com.alelo.consumer.consumerpat.application.ports.out.card;

import java.math.BigDecimal;

public interface RechargeCardOutputPort {
    void recharge(String cardNumber, BigDecimal amount);
}
