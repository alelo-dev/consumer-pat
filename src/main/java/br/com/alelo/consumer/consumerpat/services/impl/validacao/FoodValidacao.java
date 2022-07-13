package br.com.alelo.consumer.consumerpat.services.impl.validacao;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

/**
 * @author renanravelli
 */

public class FoodValidacao extends Validacao {

  private final ConsumerRepository repository;

  public FoodValidacao(
      Validacao proximaValidacao,
      ConsumerRepository repository) {
    super(proximaValidacao);
    this.repository = repository;
  }

  @Override
  public void executar(Consumer consumer) {
    repository.findByCardNumber(consumer.getFoodCardNumber())
        .ifPresent(c -> {
          throw new BusinessException(
              "J\u00E1 existe um cart\u00E3o com o n\u00FAmero do 'Food' informado.");
        });
  }
}
