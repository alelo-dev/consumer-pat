package br.com.alelo.consumer.consumerpat.business;

import br.com.alelo.consumer.consumerpat.contants.CardType;
import br.com.alelo.consumer.consumerpat.contants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.IllegalParameterException;
import br.com.alelo.consumer.consumerpat.exception.InvalidOperationException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class CardBusiness {

    @Autowired
    private CardRepository repository;

    @Transactional
    public void buy(EstablishmentType establishmentType, String establishmentName, String cardNumber, String productDescription, Double value) {
        if (Objects.isNull(establishmentType))
            throw new IllegalParameterException(new ErrorResponse("Tipo de estabelecimenmto não informado"));

        if (Objects.isNull(cardNumber) || cardNumber.isEmpty())
            throw new IllegalParameterException(new ErrorResponse("Número do cartão não informado"));

        if (Objects.isNull(value))
            value = 0d;

        Card card = this.getCardByCardNumberAndCardType(cardNumber, establishmentType.getCardTypeAllowed());
        value = establishmentType.applyBuyRule(value);

        if (value > card.getBalance())
            throw new InvalidOperationException(new ErrorResponse("Saldo insuficiente", Pair.of("balance", card.getBalance())));

        card.setBalance(card.getBalance() - value);

        card.getExtracts().add(Extract
                .builder()
                .establishmentName(establishmentName)
                .productDescription(productDescription)
                .card(card)
                .value(value)
                .build());

        repository.save(card);
    }

    /**
     * @param cardNumber
     * @param cardType
     * @param value
     * @return Novo saldo
     */
    @Transactional
    public Double setBalance(String cardNumber, CardType cardType, Double value) {
        if (Objects.isNull(value) || value < 0)
            throw new InvalidOperationException(new ErrorResponse("Saldo não informado"));

        Card card = this.getCardByCardNumberAndCardType(cardNumber, cardType);
        card.setBalance(card.getBalance() + value);

        repository.save(card);

        return card.getBalance();
    }

    public Card getCardByCardNumberAndCardType(String cardNumber, CardType cardType) {
        return getCardByCardNumberAndCardType(cardNumber, cardType, Boolean.FALSE);
    }

    public Card getCardByCardNumberAndCardType(String cardNumber, CardType cardType, Boolean loadExtract) {
        Optional<Card> optional = repository.getCardByCardNumberAndCardType(cardNumber, cardType);

        if (optional.isEmpty())
            throw new EntityNotFoundException(new ErrorResponse("Cartão não encontrado"));

        Card card = optional.get();

        if (loadExtract)
            Hibernate.initialize(card.getExtracts());

        return card;
    }

    public Boolean existCard(String cardNumber, CardType cardType) {
        return repository.getCardByCardNumberAndCardType(cardNumber, cardType).isPresent();
    }
}
