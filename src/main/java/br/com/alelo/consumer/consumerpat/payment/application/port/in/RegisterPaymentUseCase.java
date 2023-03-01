package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.SavePaymentPort;
import br.com.alelo.consumer.consumerpat.common.domain.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardPort;
import br.com.alelo.consumer.consumerpat.payment.application.port.out.PaymentStrategy;
import br.com.alelo.consumer.consumerpat.payment.domain.PaymentTypeNotPermittedException;
import br.com.alelo.consumer.consumerpat.payment.domain.InsufficientFundException;
import br.com.alelo.consumer.consumerpat.common.application.port.out.LoadCardBalancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterPaymentUseCase {

    private final LoadCardPort loadCardPort;
    private final SavePaymentPort savePaymentPort;
    private final LoadCardBalancePort loadCardBalancePort;
    private final PaymentConverter paymentConverter;
    private final Set<PaymentStrategy> paymentStrategies;
    private final ApplicationEventPublisher eventPublisher;

    public void registerPayment(RegisterPaymentCommand command) {

        var establishMentType = CardTypeEnum.of(command.getPayment().getEstablishmentTypeId());
        var cardNumber = command.getPayment().getCardNumber();
        var card = loadCardPort.findByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException("Card not found."));

        var balanceEndDate = command.getPayment().getDate();
        var balanceBeginDate = LocalDate.of(balanceEndDate.getYear(), Month.JANUARY, 1);
        var paymentWithAdditionalValues = paymentStrategies.stream()
                .filter(paymentStrategy -> paymentStrategy.canHandle(card, establishMentType))
                .findFirst()
                .orElseThrow(() -> new PaymentTypeNotPermittedException("Payment type not permitted."))
                .calculate(paymentConverter.toEntity(card, command.getPayment()));

        var cardBalance = loadCardBalancePort.calculate(card, balanceBeginDate, balanceEndDate);

        if (!cardBalance.canWithdraw(paymentWithAdditionalValues.getAmount())) {
            throw new InsufficientFundException("Insufficient funds, payment not authorized.");
        }

        savePaymentPort.save(paymentWithAdditionalValues);
        eventPublisher.publishEvent(paymentWithAdditionalValues);
    }
}
