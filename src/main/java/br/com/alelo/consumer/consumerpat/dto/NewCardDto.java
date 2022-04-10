package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewCardDto implements Serializable {

    private static final long serialVersionUID = 5165549691548487286L;

	private String idConsumer;
	
    private TypeCardDto typeCard;

    private BigDecimal initialBalanace;

}
