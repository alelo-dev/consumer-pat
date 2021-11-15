package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCardException;
import br.com.alelo.consumer.consumerpat.model.EstablishmentType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.security.InvalidParameterException;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService {

  @Autowired
  ConsumerRepository repository;

  @Autowired
  ExtractRepository extractRepository;

  /*
   * Deve creditar(adicionar) um valor(value) em um no cartão.
   * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
   * para isso deve usar o número do cartão(cardNumber) fornecido.
   */
  public void setCardBalance(int cardNumber, double value) throws ConsumerCardException {
    Consumer consumer = null;
    consumer = repository.findByDrugstoreNumber(cardNumber);
    if(consumer != null) {
      // é cartão de farmácia
      consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
    } else {

      consumer = repository.findByFoodCardNumber(cardNumber);
      if(consumer != null) {
        // é cartão de refeição
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
      } else {
        // É cartão de combustivel
        consumer = repository.findByFuelCardNumber(cardNumber);
        if(consumer != null) {
          consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
        } else {
          throw new ConsumerCardException("Número de cartão não encontrado!");
        }
      }
    }
    repository.save(consumer);
  }

  /* Os valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
   *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão de alimentação
   */
  public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    Consumer consumer = null;

    EstablishmentType type = EstablishmentType.getByValue(establishmentType);
    if (type == null) {
      throw new InvalidParameterException("Tipo de estabelecimento desconhecido.");
    }
    value = type.paymentCalculator(value);
    switch (type) {
      case FOOD :
        consumer = repository.findByFoodCardNumber(cardNumber);
        consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
        repository.save(consumer);
        break;

      case DRUG_STORE:
        consumer = repository.findByDrugstoreNumber(cardNumber);
        consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
        repository.save(consumer);
        break;

      case FUEL:
        consumer = repository.findByFuelCardNumber(cardNumber);
        consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
        repository.save(consumer);
        break;

      default:
        throw new InvalidParameterException("Tipo de estabelecimento deve ser implementado.");
    }

    Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
    extractRepository.save(extract);
  }

}
