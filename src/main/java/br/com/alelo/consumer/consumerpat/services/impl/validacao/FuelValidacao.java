package br.com.alelo.consumer.consumerpat.services.impl.validacao;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

/**
 * @author renanravelli
 */

public class FuelValidacao extends Validacao {

  private final ConsumerRepository repository;

  public FuelValidacao(ConsumerRepository repository) {
    super(null);
    this.repository = repository;
  }

  @Override
  public void executar(Consumer consumer) {
    repository.findByCardNumber(consumer.getFuelCardNumber()).ifPresent(c -> {
      throw new BusinessException(
          "J\u00E1 existe um cart\u00E3o com o n\u00FAmero do 'Fuel' informado.");
    });
  }
}
