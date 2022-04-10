package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtractDto implements Serializable {

	private static final long serialVersionUID = -4417602917436626209L;
	 
	private EstablishmentDto stablishment;
    
    private String productDescription;
    
    private LocalDate dateBuy;
    
    private CardDto card;
    
    private BigDecimal value;
}

