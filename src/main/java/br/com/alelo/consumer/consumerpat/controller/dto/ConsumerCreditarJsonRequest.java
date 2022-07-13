package br.com.alelo.consumer.consumerpat.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author renanravelli
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ConsumerCreditarJsonRequest", description = "Informa\u00E7\u00F5es para creditar uma conta.")
public class ConsumerCreditarJsonRequest {

  @ApiModelProperty(name = "value", value = "Value", position = 1)
  private BigDecimal value;

}
