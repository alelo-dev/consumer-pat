package br.com.alelo.consumer.consumerpat.service.dto.request;

import lombok.Data;

@Data
public class CardsRequest {
	
	private Long id;
	
	private int foodCardNumber;
    
	private double foodCardBalance;

    private int fuelCardNumber;
    
    private double fuelCardBalance;

    private int drugstoreNumber;
   
    private double drugstoreCardBalance;
}
