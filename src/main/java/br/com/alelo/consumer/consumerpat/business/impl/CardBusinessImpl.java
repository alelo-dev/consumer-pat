package br.com.alelo.consumer.consumerpat.business.impl;

import br.com.alelo.consumer.consumerpat.business.CardBusiness;
import br.com.alelo.consumer.consumerpat.business.TransactionCardBusiness;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.dto.DtoBuy;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Objects;

@Service
public class CardBusinessImpl implements CardBusiness {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private ExtractRepository extractRepository;


  private TransactionCardBusiness getTransactionCardBusiness(EstablishmentTypeEnum establishmentTypeEnum) {
    return applicationContext.getBean(establishmentTypeEnum.getClazz());
  }

  public void setBalance(long cardNumber, double value) {
    var card = cardRepository.findByCardNumber(cardNumber)
            .orElseThrow(() -> new EntityNotFoundException("Card number not found"));

    card.setBalance(card.getBalance() + value);
    cardRepository.save(card);
  }

  public void buy(DtoBuy dtoBuy) throws ProcessException {
    var establishment = EstablishmentTypeEnum.getEstablishmentType(dtoBuy.getEstablishmentType());
    if (Objects.isNull(establishment)) {
      throw new ProcessException("Establishment type invalid");
    }

    TransactionCardBusiness transactionCardBusiness = getTransactionCardBusiness(establishment);
    var value = transactionCardBusiness.value(dtoBuy);

    var card = cardRepository.findByCardTypeAndCardNumber(establishment.getType(), dtoBuy.getCardNumber())
            .orElseThrow(() -> new EntityNotFoundException("Card number not found"));

    if (value > card.getBalance()) {
      throw new ProcessException("Unauthorized transaction, insufficient funds");
    }
    card.setBalance(card.getBalance() - value);
    cardRepository.save(card);

    Extract extract = new Extract(dtoBuy.getEstablishmentName(), dtoBuy.getProductDescription(), new Date(), dtoBuy.getCardNumber(), dtoBuy.getValue());
    extractRepository.save(extract);
  }

}
