package br.com.alelo.consumer.consumerpat.services.impl.validacao;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;

/**
 * @author renanravelli
 */

public class NumeroCartaoValidacao extends Validacao {

  public NumeroCartaoValidacao(Validacao proximaValidacao) {
    super(proximaValidacao);
  }

  @Override
  public void executar(Consumer consumer) {

    int drugstoreNumber = consumer.getDrugstoreNumber();

    int foodCardNumber = consumer.getFoodCardNumber();

    int fuelCardNumber = consumer.getFuelCardNumber();

    boolean hasNumeroIgual =
        (drugstoreNumber == foodCardNumber) || (drugstoreNumber == fuelCardNumber) || (
            foodCardNumber == fuelCardNumber);

    if (hasNumeroIgual) {
      throw new BusinessException(
          "O n\u00FAmero dos cart\u00F5es n\u00E3o podem ser iguais. Os mesmos devem ser \u00FAnicos.");
    }
  }
}
