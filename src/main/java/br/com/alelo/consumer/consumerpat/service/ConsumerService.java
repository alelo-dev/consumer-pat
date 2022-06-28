package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class ConsumerService {

  @Autowired
  ExtractRepository extractRepository;

  @Autowired
  ConsumerRepository repository;

  public List<Consumer> getAllConsumers() {
    int page = 0;
    int size = 500;

    PageRequest pageRequest = PageRequest.of(
      page,
      size,
      Sort.Direction.ASC,
      "dateBuy"
    );
    return (List<Consumer>) new PageImpl<>(
      repository.findAll(),
      pageRequest,
      size
    );
  }

  public void validateConsumer(
    Consumer consumer,
    Integer cardNumber,
    Double value
  ) {
    if (consumer != null) {
      consumer.setDrugstoreCardBalance(
        consumer.getDrugstoreCardBalance() + value
      );
      repository.save(consumer);
    } else {
      consumer = repository.findByFoodCardNumber(cardNumber);
      if (consumer != null) {
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
        repository.save(consumer);
      } else {
        consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
        repository.save(consumer);
      }
    }
    return;
  }

  public void purchase(
    Integer establishmentNameId,
    Integer establishmentType,
    String establishmentName,
    Integer cardNumber,
    String productDescription,
    Double value
  )
    throws Exception {
    Consumer consumer = null;
    try {
      if (establishmentType == CardType.FOOD.getCod()) {
        Double cashback = (value / 100) * 10;
        value = value - cashback;

        consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
        repository.save(consumer);
      } else if (establishmentType == CardType.PHARMACY.getCod()) {
        consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.setDrugstoreCardBalance(
          consumer.getDrugstoreCardBalance() - value
        );
        repository.save(consumer);
      } else if (establishmentType == CardType.FUEL.getCod()) {
        Double tax = (value / 100) * 35;
        value = value + tax;

        consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
        repository.save(consumer);
      }
    } catch (Exception e) {
      throw new Exception("ESTABLISHMENT TYPE ERROR !");
    }

    Extract extract = new Extract(
      null,
      establishmentNameId,
      establishmentName,
      productDescription,
      new Date(),
      cardNumber,
      value
    );
    extractRepository.save(extract);
  }
}
