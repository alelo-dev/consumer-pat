package br.com.alelo.consumer.consumerpat.domain.payload;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CardPayload {
	
	@ApiModelProperty(value = "Attached card number", example = "5513570566784056")
	private String number;
	@ApiModelProperty(value = "Attached card type. Can be FOOD, DRUGSTORE or FUEL", example = "FOOD")
	private CardType type;

}
