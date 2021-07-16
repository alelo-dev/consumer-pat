package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.enums.TypeEstablishment.FOOD;
import static br.com.alelo.consumer.consumerpat.enums.TypeEstablishment.FUEL;

@Service
public class CardService extends BaseServiceImpl<Card, CardRepository> {

    @Value( "${tax.food}" )
    private BigDecimal foodCashback;

    @Value( "${tax.fuel}" )
    private BigDecimal fuelTax;

    private final ExtractService extractService;

    public CardService(CardRepository repository, ExtractService extractService) {
        super(repository);
        this.extractService = extractService;
    }

    @Override
    public Card save(Card entity) {

        if (entity.getCardBalance().signum() < 0) {
            throw new BusinessException("Insufficient balance for this operation");
        }

        return super.save(entity);
    }

    public void updateBalance(long cardNumber, BigDecimal balance) {

        Optional<Card> card = Optional.ofNullable(getRepository().findByCardNumber(cardNumber));

        card.ifPresentOrElse(
                cardValue -> {
                    cardValue.setCardBalance(cardValue.getCardBalance().add(balance));
                    save(cardValue);
                },
                () -> {
                    throw new CardNotFoundException(cardNumber);
                });
    }

    public void buy(BuyDTO buy) {

        BigDecimal buyValue = buy.getValue();

        Card card = getRepository().findByCardNumber(buy.getCardNumber());

        preValidations(buy, card);
        applyPercentage(buy, card);
        save(card);

        Extract extract = Extract.builder()
                .establishmentName(buy.getEstablishmentName())
                .productDescription(buy.getProductDescription())
                .dateBuy(LocalDateTime.now())
                .card(card)
                .purchaseValue(buyValue)
                .value(buy.getValue()).build();

        extractService.save(extract);
    }

    protected void applyPercentage(BuyDTO buy, Card card) {

        if (FOOD.equals(buy.getTypeEstablishment())) {
            BigDecimal cashback = buy.getValue().multiply(foodCashback);
            buy.setValue(buy.getValue().subtract(cashback));
        }

        if (FUEL.equals(buy.getTypeEstablishment())) {
            BigDecimal tax = buy.getValue().multiply(fuelTax);
            buy.setValue(buy.getValue().add(tax));
        }

        card.setCardBalance(card.getCardBalance().subtract(buy.getValue()));
    }

    private void preValidations(BuyDTO buy, Card card) {

        if (Objects.isNull(card)) {
            throw new CardNotFoundException(buy.getCardNumber());
        }

        if (buy.getTypeEstablishment().toString().compareTo(card.getTypeCard().toString()) != 0) {
            throw new BusinessException("The card cannot be used in this type of establishment.");
        }
    }
}
