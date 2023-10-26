package br.com.alelo.consumer.consumerpat.application.core.usecase.card;

import br.com.alelo.consumer.consumerpat.application.ports.in.card.RechargeCardInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.card.RechargeCardOutputPort;

import java.math.BigDecimal;

public class RechargeCardUseCase implements RechargeCardInputPort {

    private final RechargeCardOutputPort rechargeCardOutputPort;

    public RechargeCardUseCase(RechargeCardOutputPort rechargeCardOutputPort) {
        this.rechargeCardOutputPort = rechargeCardOutputPort;
    }

    @Override
    public void recharge(String cardNumber, BigDecimal amount) {
        rechargeCardOutputPort.recharge(cardNumber, amount);
    }
}
