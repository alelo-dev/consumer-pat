package br.com.alelo.consumer.consumerpat.domain.payload;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BuyPayload {
	
	@ApiModelProperty(required = true, value = "Establishment enabled card type. Can be FOOD, DRUGSTORE or FUEL", example = "FOOD")
	private CardType establishmentType;
	@ApiModelProperty(required = true, value = "Establishment name", example = "A Restaurant")
	private String establishmentName;
	@ApiModelProperty(required = true, value = "Attached product, described by name", example = "Something to Eat")
	private String productDescription;
	@ApiModelProperty(required = true, value = "Value attached to product", example = "15.80")
	private BigDecimal value;

}
