package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.RegisterActivityUseCase;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentEventListenerTest {

    @Mock
    private RegisterActivityUseCase registerActivityUseCase;

    @InjectMocks
    private PaymentEventListener paymentEventListener;

    @Test
    void shouldProcessPaymentEvent() {

        var payment = new Payment();

        paymentEventListener.onPaymentCreated(payment);

        verify(registerActivityUseCase, times(1))
                .withdraw(payment);
    }
}