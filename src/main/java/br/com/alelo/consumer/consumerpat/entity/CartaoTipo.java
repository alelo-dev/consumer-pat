package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CartaoTipo {
  FOOD(1),
  DRUGSTORE(2),
  FUEL(3);

  private Integer code;

  public static CartaoTipo getByEstablishmentType(Integer type) {
    return Arrays.stream(CartaoTipo.values())
        .filter(cartaoTipo -> cartaoTipo.getCode().equals(type))
        .findFirst()
        .orElseThrow(BusinessException.supplier("Tipo de estabelecimento n\u00E3o mapeado."));
  }
}
