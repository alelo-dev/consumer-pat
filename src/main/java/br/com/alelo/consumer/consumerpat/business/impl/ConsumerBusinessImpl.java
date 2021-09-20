package br.com.alelo.consumer.consumerpat.business.impl;

import br.com.alelo.consumer.consumerpat.business.ConsumerBusiness;
import br.com.alelo.consumer.consumerpat.business.TransactionCardBusiness;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumerBusinessImpl implements ConsumerBusiness {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ConsumerRepository repository;

  @Autowired
  private CardRepository cardRepository;


  public List<Consumer> listAllConsumers() {
    var consumerList = repository.getAllConsumersList().stream().map(consumer1 -> {
      cardRepository.findByConsumerId(consumer1.getId()).forEach(card -> {
        EstablishmentTypeEnum establishmentTypeEnum = EstablishmentTypeEnum.getEstablishmentType(card.getCardType());
        var transactionCard = getTransactionCardBusiness(establishmentTypeEnum);
        transactionCard.setCard(consumer1, card);
      });
      return consumer1;
    });
    return consumerList.collect(Collectors.toList());
  }

  public void createConsumer(Consumer consumer) throws ProcessException {
    consumer.setId(null);
    if (repository.findByEmail(consumer.getEmail()).isPresent()) {
      throw new ProcessException("Email already exists");
    }
    var validCardNumber = Arrays.stream(EstablishmentTypeEnum.values()).filter(establishmentTypeEnum -> {
      TransactionCardBusiness transactionCardBusiness = getTransactionCardBusiness(establishmentTypeEnum);
      return transactionCardBusiness.existsNumber(consumer);
    }).findFirst();
    if (validCardNumber.isPresent()) {
      throw new ProcessException(String.format("%s number already exists",validCardNumber.get().getName()));
    }
    var consumerSaved = repository.save(consumer);
    Arrays.stream(EstablishmentTypeEnum.values()).forEach(establishmentTypeEnum -> {
      TransactionCardBusiness transactionCardBusiness = getTransactionCardBusiness(establishmentTypeEnum);
      transactionCardBusiness.saveCard(consumerSaved);
    });

  }

  private TransactionCardBusiness getTransactionCardBusiness(EstablishmentTypeEnum establishmentTypeEnum) {
    return applicationContext.getBean(establishmentTypeEnum.getClazz());
  }

  public void updateConsumer(Consumer consumer) throws ProcessException {
    if (!repository.findById(consumer.getId()).isPresent()) {
      throw  new EntityNotFoundException("Consumer not found");
    }
    var consumerSaved = repository.save(consumer);
    consumer.setId(consumerSaved.getId());
    Arrays.stream(EstablishmentTypeEnum.values()).forEach(establishmentTypeEnum -> {
      TransactionCardBusiness transactionCardBusiness = getTransactionCardBusiness(establishmentTypeEnum);
      transactionCardBusiness.saveCard(consumer);
    });
  }
}
