package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.RegisterActivityUseCase;
import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RechargeEventListenerTest {

    @Mock
    private RegisterActivityUseCase registerActivityUseCase;

    @InjectMocks
    private RechargeEventListener rechargeEventListener;

    @Test
    void shouldProcessEvent() {

        var recharge = Recharge.of(new Card(), BigDecimal.TEN, LocalDate.now());

        rechargeEventListener.onNewRecharge(recharge);

        verify(registerActivityUseCase, times(1))
                .deposit(recharge);
    }
}