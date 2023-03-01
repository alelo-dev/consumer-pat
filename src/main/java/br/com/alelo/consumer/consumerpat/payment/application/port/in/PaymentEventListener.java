package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.RegisterActivityUseCase;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final RegisterActivityUseCase registerActivityUseCase;

    @EventListener
    public void onPaymentCreated(Payment paymentCreated) {
        registerActivityUseCase.withdraw(paymentCreated);
    }
}
