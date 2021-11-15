package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCardException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumerService {

  @Autowired
  ConsumerRepository consumerRepository;

  public List<Consumer> listAllConsumers() {
    return consumerRepository.getAllConsumersList();
  }

  public void insert(Consumer consumer) {
    consumerRepository.save(consumer);
  }

  public void update(Consumer consumer) throws ConsumerCardException {
    Optional<Consumer> ConsumerOld = consumerRepository.findById(consumer.getId());
    if (ConsumerOld.get().getFoodCardBalance() != consumer.getFoodCardBalance()) {
      throw new ConsumerCardException("Saldo do cartão Alimentação não pode ser alterado nessa operação.");
    }
    if (ConsumerOld.get().getDrugstoreCardBalance() != consumer.getDrugstoreCardBalance()) {
      throw new ConsumerCardException("Saldo do cartão Farmácia não pode ser alterado nessa operação.");
    }
    if (ConsumerOld.get().getFuelCardBalance() != consumer.getFuelCardBalance()) {
      throw new ConsumerCardException("Saldo do cartão Combustível não pode ser alterado nessa operação.");
    }

    consumerRepository.save(consumer);
  }

}
