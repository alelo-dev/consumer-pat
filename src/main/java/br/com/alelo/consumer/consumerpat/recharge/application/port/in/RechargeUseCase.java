package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RechargeUseCase {

    private final LoadCardPort loadCardPort;
    private final ApplicationEventPublisher eventPublisher;

    public void recharge(RechargeCommand command) {

        var card = loadCardPort.findByNumber(command.getCardNumber())
                .orElseThrow(() -> new CardNotFoundException("Card not found"));

        var newRecharge = Recharge.of(card, command.getAmount(), command.getRechargedAt());

        eventPublisher.publishEvent(newRecharge);
    }
}
