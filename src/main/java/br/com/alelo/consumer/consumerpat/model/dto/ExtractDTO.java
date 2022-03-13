package br.com.alelo.consumer.consumerpat.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractDTO {

	private Integer establishmentNameId;

	private String establishmentName;

	private String productDescription;

	private Date dateBuy;

	private Integer cardNumber;

	private Double value;
}
