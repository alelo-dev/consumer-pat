package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBalanceDto implements Serializable {

	private static final long serialVersionUID = 149403878115677019L;

	private Long cardNumber;
	
	private BigDecimal value;
	
}
