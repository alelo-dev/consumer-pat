package br.com.alelo.consumer.consumerpat.adapters.out.payments;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.PaymentsRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.mapper.PaymentsEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import br.com.alelo.consumer.consumerpat.application.ports.out.payments.PaymentsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PaymentsAdapter implements PaymentsOutputPort {

    private final CardCustomerRepository cardCustomerRepository;
    private final PaymentsRepository paymentsRepository;
    private final PaymentsEntityMapper paymentsEntityMapper;

    public void payment(Payments payments) {
        var cardCustomer = cardCustomerRepository.findByCardNumber(payments.getCardNumber());
        if (cardCustomer.isPresent() && payments.getEstablishment().getEstablishmentType().equals("FOOD") && cardCustomer.get().getCardType().equals("FOOD")) {
            var discountRate = 10;
            var cashback  = payments.getAmount().divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(discountRate));

            cardCustomer.get().setCardBalance(cardCustomer.get().getCardBalance().subtract(payments.getAmount()));
            cardCustomer.get().setCardBalance(cardCustomer.get().getCardBalance().add(cashback));
            cardCustomerRepository.save(cardCustomer.get());
            paymentsRepository.save(paymentsEntityMapper.toPaymentsEntity(payments));

            return;
        }

        if(cardCustomer.isPresent() && payments.getEstablishment().getEstablishmentType().equals("DRUGSTORE") && cardCustomer.get().getCardType().equals("DRUGSTORE")) {
            cardCustomer.get().setCardBalance(cardCustomer.get().getCardBalance().subtract(payments.getAmount()));
            cardCustomerRepository.save(cardCustomer.get());
            paymentsRepository.save(paymentsEntityMapper.toPaymentsEntity(payments));

            return;
        }
        if(cardCustomer.isPresent() && payments.getEstablishment().getEstablishmentType().equals("FUEL") && cardCustomer.get().getCardType().equals("FUEL")) {
            var discountRate = 35;
            BigDecimal cashback = payments.getAmount().divide(BigDecimal.valueOf(100))
                    .multiply(BigDecimal.valueOf(discountRate));

            cardCustomer.get().setCardBalance(cardCustomer.get().getCardBalance().subtract(payments.getAmount()));
            cardCustomer.get().setCardBalance(cardCustomer.get().getCardBalance().add(cashback));
            cardCustomerRepository.save(cardCustomer.get());
            paymentsRepository.save(paymentsEntityMapper.toPaymentsEntity(payments));
        }

        throw new RuntimeException("Establishment not authorized,");
    }
}


