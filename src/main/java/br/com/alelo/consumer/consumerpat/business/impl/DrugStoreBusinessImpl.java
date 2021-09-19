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
public class DrugStoreBusinessImpl implements TransactionCardBusiness {

  @Autowired
  private ConsumerRepository repository;

  @Autowired
  private CardRepository cardRepository;

  public double value(DtoBuy dtoBuy) {
    return dtoBuy.getValue();
  }

  public void saveCard(Consumer consumer) {
    if (Objects.nonNull(consumer.getDrugstoreNumber())
            && consumer.getDrugstoreCardBalance() > 0){
      Card cardRepo = cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.DRUG_STONE.getType(), consumer.getDrugstoreNumber())
              .orElse(Card.builder().build());
      cardRepository.save(Card.builder()
              .consumerId(consumer.getId())
              .id(cardRepo.getId())
              .balance(consumer.getDrugstoreCardBalance())
              .cardNumber(consumer.getDrugstoreNumber())
              .cardType(EstablishmentTypeEnum.DRUG_STONE.getType()).build());
    }
  }

  @Override
  public void setCard(Consumer consumer, Card card) {
    consumer.setDrugstoreCardBalance(card.getBalance());
    consumer.setDrugstoreNumber(card.getCardNumber());
  }


  public boolean existsNumber(Consumer consumer) {
    if (Objects.isNull(consumer.getDrugstoreNumber())){
      return false;
    }
    return cardRepository.findByCardTypeAndCardNumber(EstablishmentTypeEnum.DRUG_STONE.getType(), consumer.getDrugstoreNumber()).isPresent();
  }

}
