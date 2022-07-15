package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.CartaoTipo;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author renanravelli
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoJsonRequest {

  private CartaoTipo tipo;

  private Integer number;

  private BigDecimal balance;

}
