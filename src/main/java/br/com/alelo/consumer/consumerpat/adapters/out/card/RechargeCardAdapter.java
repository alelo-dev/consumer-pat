package br.com.alelo.consumer.consumerpat.adapters.out.card;

import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.application.ports.out.card.RechargeCardOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RechargeCardAdapter implements RechargeCardOutputPort {

    private final CardCustomerRepository cardCustomerRepository;

    @Override
    public void recharge(String cardNumber, BigDecimal amount) {
        var customerCardEntity = cardCustomerRepository.findByCardNumber(cardNumber);

        customerCardEntity.ifPresent(cardCustomerEntity -> {
            cardCustomerEntity.setCardBalance(amount);
            cardCustomerRepository.save(cardCustomerEntity);
        });
    }
}
