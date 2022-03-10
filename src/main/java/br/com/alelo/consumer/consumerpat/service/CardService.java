package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.exception.InvalidCardException;
import br.com.alelo.consumer.consumerpat.exception.NoFundsException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CardService {
  private final CardRepository cardRepository;

  public Card buy(Purchase purchase) throws NoFundsException, InvalidCardException {
    if (isCardNotFound(purchase)) {
      throw new NoSuchElementException("card not found");
    }
    Card card = cardRepository.findByCardNumber(purchase.getCardNumber());
    if (IsCardHasFunds(purchase, card)) {
      throw new NoFundsException("no funds");
    }

    buyCard(purchase, card);

    return card;
  }

  private void buyCard(Purchase purchase, Card card) throws InvalidCardException {
    if (isFOODBuy(purchase)) {

      Double discount = (purchase.getValue() / 100) * 10;
      Double value = purchase.getValue() - discount;
      card.setCardBalance(card.getCardBalance() - value);

    } else if (isDRUGSTOREBuy(purchase)) {
      card.setCardBalance(card.getCardBalance() - purchase.getValue());
    } else if (isFUELBuy(purchase)) {
      Double tax = (purchase.getValue() / 100) * 35;
      Double value = purchase.getValue() + tax;
      card.setCardBalance(card.getCardBalance() - value);
    } else {
      throw new InvalidCardException("Invalid type of Card.");
    }
  }

  private boolean isFUELBuy(Purchase purchase) {
    return purchase.getEstablishmentType() == CardType.FUEL.getCode()
        && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode()
            == CardType.FUEL.getCode();
  }

  private boolean isDRUGSTOREBuy(Purchase purchase) {
    return purchase.getEstablishmentType() == CardType.DRUGSTORE.getCode()
        && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode()
            == CardType.DRUGSTORE.getCode();
  }

  private boolean isFOODBuy(Purchase purchase) {
    return purchase.getEstablishmentType() == CardType.FOOD.getCode()
        && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode()
            == CardType.FOOD.getCode();
  }

  private boolean IsCardHasFunds(Purchase purchase, Card card) {
    return card.getCardBalance() < purchase.getValue();
  }

  private boolean isCardNotFound(Purchase purchase) {
    return Objects.isNull(cardRepository.findByCardNumber(purchase.getCardNumber()));
  }

  public Card setCardBalance(String cardNumber, Double value) {
    Card card = cardRepository.findByCardNumber(cardNumber);
    if (Objects.nonNull(card)) {
      card.setCardBalance(value);
      return cardRepository.save(card);
    }
    throw new NoSuchElementException("Card" + cardNumber + " not found");
  }

  public Card save(@RequestBody Card card) {
    return cardRepository.save(card);
  }
}
