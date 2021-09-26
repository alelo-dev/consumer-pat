package br.com.alelo.consumer.consumerpat.service.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsumerRequest {
	
    private Long id;
    
    private String name;
    
    private int documentNumber;
    
    private LocalDate birthDate;
    
    private ContactRequest contactRequest;
    
    private AddressRequest addressRequest;
    
    private CardsRequest cardsRequest;
}
