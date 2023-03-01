package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardPort;
import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.recharge.domain.Recharge;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RechargeUseCaseTest {

    @Mock
    private LoadCardPort loadCardPort;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private RechargeUseCase rechargeUseCase;

    @Nested
    class SuccessCases {

        @Test
        void shouldRechargeCardSuccessfully() {

            var newRechage = new NewRecharge("1111111111111111",
                    BigDecimal.ZERO,
                    LocalDate.now());

            var command = RechargeCommand.of(newRechage.getCardNumber(),
                    newRechage.getAmount(),
                    newRechage.getRechargedAt());

            given(loadCardPort.findByNumber(newRechage.getCardNumber()))
                    .willReturn(Optional.of(new Card()));

            rechargeUseCase.recharge(command);

            verify(loadCardPort, times(1))
                    .findByNumber(newRechage.getCardNumber());

            verify(eventPublisher, times(1))
                    .publishEvent(any(Recharge.class));
        }
    }
}