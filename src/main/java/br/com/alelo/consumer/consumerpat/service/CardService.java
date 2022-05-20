package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository repository;
  private final ExtractService extractService;

  public Card addConsumerCard(Card card) {
    return repository.save(card);
  }

  public void removeConsumerCard(int id) throws HibernateException {
    var card = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new HibernateException("Card not found"));
    card.setActive(false);

    repository.save(card);
  }

  public Card payment(int establishmentType, String establishmentName, String cardNumber, String productDescription,
      double value) {
    var card = findByCardNumber(cardNumber);

    double newBalance = (card.getCardBalance() - value);
    if (newBalance < 0)
      throw new RuntimeException("Insufficient funds");

    card.setCardBalance(newBalance);

    card = repository.save(card);
    Extract extract = new Extract(establishmentName, productDescription, new Date(), card.getCardNumber(), value);
    extractService.saveExtract(extract);
    return card;
  }

  public Card findByCardNumber(String cardNumber) {
    return repository.findByCardNumberAndActiveTrue(cardNumber)
        .orElseThrow(() -> new RuntimeException("Card not found"));
  }

  public void setCardbalance(String cardNumber, double value) {

    var card = repository.findByCardNumberAndActiveTrue(cardNumber)
        .orElseThrow(() -> new RuntimeException("Card not found"));

    card.setCardBalance(card.getCardBalance() + value);
    repository.save(card);
  }
}