package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeCardDto implements Serializable {

	private static final long serialVersionUID = 690117225004895365L;
    
	private Integer idTypeCard;
	
	private String typeCard;
	
}
