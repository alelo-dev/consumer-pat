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
public class FoodCardBusinessImpl implements TransactionCardBusiness {

  @Autowired
  private ConsumerRepository repository;

  @Autowired
  private CardRepository cardRepository;
  public double value(DtoBuy dtoBuy) {
    var value = dtoBuy.getValue();
    Double cashback  = (value / 100) * 10;
    return value - cashback;
  }

  public void saveCard(Consumer consumer) {
    if (Objects.nonNull(consumer.getFoodCardNumber())
          && consumer.getFoodCardBalance() > 0){
        Card cardRepo = cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.FOOD.getType(), consumer.getFoodCardNumber())
                .orElse(Card.builder().build());
      cardRepository.save(Card.builder()
              .consumerId(consumer.getId())
              .id(cardRepo.getId())
              .balance(consumer.getFoodCardBalance())
              .cardNumber(consumer.getFoodCardNumber())
              .cardType(EstablishmentTypeEnum.FOOD.getType()).build());
    }
  }


  public boolean existsNumber(Consumer consumer) {
    if (Objects.isNull(consumer.getFoodCardNumber())){
      return false;
    }
    return cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.FOOD.getType(), consumer.getFoodCardNumber()).isPresent();
  }

  @Override
  public void setCard(Consumer consumer, Card card) {
    consumer.setFoodCardBalance(card.getBalance());
    consumer.setFoodCardNumber(card.getCardNumber());
  }
}
