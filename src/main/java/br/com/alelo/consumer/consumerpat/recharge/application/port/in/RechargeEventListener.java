package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.RegisterActivityUseCase;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class RechargeEventListener {

    private final RegisterActivityUseCase registerActivityUseCase;

    @EventListener
    public void onNewRecharge(Recharge recharge) {
        registerActivityUseCase.deposit(recharge);
    }
}
