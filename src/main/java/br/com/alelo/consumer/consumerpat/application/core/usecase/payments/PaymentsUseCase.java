package br.com.alelo.consumer.consumerpat.application.core.usecase.payments;

import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import br.com.alelo.consumer.consumerpat.application.ports.in.payments.PaymentsInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.out.payments.PaymentsOutputPort;

public class PaymentsUseCase implements PaymentsInputPort {

    private final PaymentsOutputPort paymentsOutputPort;

    public PaymentsUseCase(PaymentsOutputPort paymentsOutputPort) {
        this.paymentsOutputPort = paymentsOutputPort;
    }

    @Override
    public void payment(Payments payments) {
        paymentsOutputPort.payment(payments);
    }
}