package br.com.alelo.consumer.consumerpat.service;

import org.hibernate.HibernateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.constants.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerService {

  private final ConsumerRepository repository;

  private final CardService cardService;

  public Consumer createConsumer(Consumer consumer) {
    return repository.save(consumer);
  }

  public Page<Consumer> consumerList(Pageable pageable) {
    return repository.findAllConsumers(pageable);
  }

  public Consumer updateConsumer(Consumer consumer) throws HibernateException {

    repository.findById(consumer.getId())
        .orElseThrow(() -> new HibernateException("Consumer not found"));
    return repository.save(consumer);

  }

  public void buy(int establishmentType, String establishmentName, String cardNumber, String productDescription,
      double value) throws RuntimeException {
    /*
     * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
     * tipo do estabelecimento da compra.
     * Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o
     * valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    cardService.payment(establishmentType, establishmentName, cardNumber, productDescription, value);

  }

}