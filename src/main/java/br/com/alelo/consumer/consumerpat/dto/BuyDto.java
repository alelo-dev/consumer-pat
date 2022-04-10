package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyDto implements Serializable {

	private static final long serialVersionUID = -7908883022409537077L;

	private String idEstablishment;

	private CardDto cardDto;
	
	private String productDescription;

	private BigDecimal value;
}
