package br.com.alelo.consumer.consumerpat.business.impl;

import br.com.alelo.consumer.consumerpat.business.TransactionCardBusiness;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FuelBusinessImpl implements TransactionCardBusiness {

  @Autowired
  private ConsumerRepository repository;

  @Autowired
  private CardRepository cardRepository;

  public double value(DtoBuy dtoBuy) {
    var value = dtoBuy.getValue();
    Double tax  = (value / 100) * 35;
    return value + tax;
  }

  public void saveCard(Consumer consumer) {
    if (Objects.nonNull(consumer.getFuelCardNumber())
            && consumer.getFuelCardBalance() > 0){
      Card cardRepo = cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.FUEL.getType(), consumer.getFuelCardNumber())
              .orElse(Card.builder().build());
      cardRepository.save(Card.builder()
              .consumerId(consumer.getId())
              .id(cardRepo.getId())
              .balance(consumer.getFuelCardBalance())
              .cardNumber(consumer.getFuelCardNumber())
              .cardType(EstablishmentTypeEnum.FUEL.getType()).build());
    }
  }



  public boolean existsNumber(Consumer consumer) {
    if (Objects.isNull(consumer.getFuelCardNumber())){
      return false;
    }
    return cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.FUEL.getType(), consumer.getFuelCardNumber()).isPresent();
  }

  @Override
  public void setCard(Consumer consumer, Card card) {
    consumer.setFuelCardBalance(card.getBalance());
    consumer.setFuelCardNumber(card.getCardNumber());
  }
}