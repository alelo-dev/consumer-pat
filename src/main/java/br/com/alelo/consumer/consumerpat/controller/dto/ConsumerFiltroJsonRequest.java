package br.com.alelo.consumer.consumerpat.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "ConsumerFiltroJsonRequest", description = "Informa\u00E7\u00F5es do filtro para realizar pesquisa.")
public class ConsumerFiltroJsonRequest {

  @ApiModelProperty(name = "pagina", value = "N\u00FAmero da p\u00E1gina", example = "0", position = 1)
  private int pagina = 0;

  @ApiModelProperty(name = "tamanho", value = "Tamanho da p\u00E1gina", example = "10", position = 2)
  private int tamanho = 10;

}
