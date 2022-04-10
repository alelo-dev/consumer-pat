package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceCardDto implements Serializable {

	private static final long serialVersionUID = 951027801278222060L;

	private String idCard;
	
	private String idConsumer;

}
