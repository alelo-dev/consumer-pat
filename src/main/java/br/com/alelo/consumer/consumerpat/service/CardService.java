package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod_ = {@Autowired})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardService extends CrudService<Card, CardRepository> {

    @Getter
    CardRepository repository;

    public Optional<Card> findByNumberAndProductType(final String cardNumber, final ProductType productType) {
        return repository.findByNumberAndProductType(cardNumber, productType);
    }

    public Optional<Card> findByNumberAndCustomer(final String cardNumber, final Customer customer) {
        return repository.findByNumberAndCustomer(cardNumber, customer);
    }

    public void recharge(final Card card, final double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value can't be negative");
        }
        card.setBalance(card.getBalance() + value);
        save(card);
    }

}
