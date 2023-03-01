package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardBalancePort;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardPort;
import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardBalance;
import br.com.alelo.consumer.consumerpat.common.domain.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.common.domain.CardType;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.DrugstorePaymentStrategy;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.FoodPaymentStrategy;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.FuelPaymentStrategy;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.PaymentStrategy;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.SavePaymentPort;
import br.com.alelo.consumer.consumerpat.payment.domain.InsufficientFundException;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterPaymentUseCaseTest {


    @Mock
    private LoadCardPort loadCardPort;

    @Mock
    private SavePaymentPort savePaymentPort;

    @Mock
    private LoadCardBalancePort loadCardBalancePort;

    @Spy
    private PaymentConverter paymentConverter = new PaymentConverter();

    @Spy
    private Set<PaymentStrategy> paymentStrategies = new HashSet<>();
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private RegisterPaymentUseCase registerPaymentUseCase;

    @BeforeEach
    public void beforeAll() {

        paymentStrategies.add(new FoodPaymentStrategy());
        paymentStrategies.add(new DrugstorePaymentStrategy());
        paymentStrategies.add(new FuelPaymentStrategy());
    }

    @Nested
    class SuccessCases {

        @Test
        void shouldRegisterPaymentSuccessfully() {

            var newPayment = NewPayment.of(1,
                    "Ifood",
                    "1111111111111111",
                    "Hamburger",
                    BigDecimal.valueOf(15.99),
                    LocalDate.now());

            var command = RegisterPaymentCommand.of(newPayment);

            var cardType = new CardType();
            cardType.setCardTypeId(CardTypeEnum.FOOD.getValue());

            var card = new Card();
            card.setNumber("1111111111111111");
            card.setType(cardType);

            var balanceEndDate = command.getPayment().getDate();
            var balanceBeginDate = LocalDate.of(balanceEndDate.getYear(), Month.JANUARY, 1);

            var cardBalance = new CardBalance(card, BigDecimal.valueOf(30.00));

            given(loadCardPort.findByNumber(card.getNumber()))
                    .willReturn(Optional.of(card));

            given(loadCardBalancePort.calculate(card, balanceBeginDate, balanceEndDate))
                    .willReturn(cardBalance);

            registerPaymentUseCase.registerPayment(command);

            verify(savePaymentPort, times(1))
                    .save(any(Payment.class));

            verify(eventPublisher, times(1))
                    .publishEvent(any(Payment.class));
        }
    }

    @Nested
    class ErrorCases {

        @Test
        void shouldNotRegisterPayment_WhenCardNotExists() {

            var newPayment = NewPayment.of(1,
                    "Ifood",
                    "1111111111111111",
                    "Hamburger",
                    BigDecimal.valueOf(15.99),
                    LocalDate.now());

            var command = RegisterPaymentCommand.of(newPayment);

            assertThatThrownBy(() -> registerPaymentUseCase.registerPayment(command))
                    .isNotNull()
                    .isInstanceOf(CardNotFoundException.class);

            verify(loadCardBalancePort, never())
                    .calculate(any(), any(), any());

            verify(savePaymentPort, never())
                    .save(any(Payment.class));

            verify(eventPublisher, never())
                    .publishEvent(any(Payment.class));
        }

        @Test
        void shouldNotRegisterPayment_WhenBalanceCardHasInsufficientFunds() {

            var newPayment = NewPayment.of(1,
                    "Ifood",
                    "1111111111111111",
                    "Hamburger",
                    BigDecimal.valueOf(100.00),
                    LocalDate.now());

            var command = RegisterPaymentCommand.of(newPayment);

            var cardType = new CardType();
            cardType.setCardTypeId(CardTypeEnum.FOOD.getValue());

            var card = new Card();
            card.setNumber("1111111111111111");
            card.setType(cardType);

            var balanceEndDate = command.getPayment().getDate();
            var balanceBeginDate = LocalDate.of(balanceEndDate.getYear(), Month.JANUARY, 1);

            var cardBalance = new CardBalance(card, BigDecimal.valueOf(30.00));

            given(loadCardPort.findByNumber(card.getNumber()))
                    .willReturn(Optional.of(card));

            given(loadCardBalancePort.calculate(card, balanceBeginDate, balanceEndDate))
                    .willReturn(cardBalance);

            assertThatThrownBy(() -> registerPaymentUseCase.registerPayment(command))
                    .isNotNull()
                    .isInstanceOf(InsufficientFundException.class);

            verify(loadCardBalancePort, times(1))
                    .calculate(card, balanceBeginDate, balanceEndDate);

            verify(savePaymentPort, never())
                    .save(any(Payment.class));

            verify(eventPublisher, never())
                    .publishEvent(any(Payment.class));
        }
    }
}