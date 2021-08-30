package br.com.alelo.consumer.consumerpat.domain.response;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
	
	@ApiModelProperty(value = "Attached card number", example = "5513570566784056")
	private String number;
	@ApiModelProperty(value = "Attached card type. Can be FOOD, DRUGSTORE or FUEL", example = "FOOD")
	private CardType type;
	@ApiModelProperty(value = "Actual card balance", example = "52.89")
	private BigDecimal balance;

}
