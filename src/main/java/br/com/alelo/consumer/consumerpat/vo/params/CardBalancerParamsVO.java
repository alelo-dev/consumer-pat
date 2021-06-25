package br.com.alelo.consumer.consumerpat.vo.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardBalancerParamsVO {

	private int cardNumber;
	private double value;
	
}
