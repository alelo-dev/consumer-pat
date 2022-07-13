package br.com.alelo.consumer.consumerpat.services.impl.validacao;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 * @author renanravelli
 */

@AllArgsConstructor
public abstract class Validacao {

  private Validacao proximaValidacao;

  protected abstract void executar(Consumer consumer);

  public void validar(Consumer consumer) {
    executar(consumer);
    Optional.ofNullable(this.proximaValidacao).ifPresent(handler -> handler.validar(consumer));
  }

}
