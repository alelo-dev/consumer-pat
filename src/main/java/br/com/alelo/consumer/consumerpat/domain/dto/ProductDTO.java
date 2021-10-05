package br.com.alelo.consumer.consumerpat.domain.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long id;
	private Integer quantity;
	private String name;
	private BigDecimal value;
	
}
