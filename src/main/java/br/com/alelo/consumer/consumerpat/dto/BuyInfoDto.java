package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import br.com.alelo.consumer.consumerpat.entity.EstablishmentType;
import lombok.Data;

@Data
public class BuyInfoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private EstablishmentType establishmentType;
	private String establishmentName; 
	private String cardNumber;
	private String productDescription; 
	private double value;
}
