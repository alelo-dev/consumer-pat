package br.com.alelo.consumer.consumerpat.vo.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyParamsVO {
	private int establishmentType;
	private String establishmentName;
	private int cardNumber;
	private String productDescription;
	private double value;

}
