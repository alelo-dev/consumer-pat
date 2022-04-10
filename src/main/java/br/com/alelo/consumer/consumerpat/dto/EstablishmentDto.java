package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstablishmentDto implements Serializable {

	private static final long serialVersionUID = 1745460750508668404L;
		  
	private String nameEstablishment;
	
}
