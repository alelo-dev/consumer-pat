package br.com.alelo.consumer.consumerpat.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrappedPurchaseDTO
 {

	private Long establishmentId;
	private List<ProductDTO> products;
	private String cardNumber;
	
}
