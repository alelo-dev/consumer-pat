package br.com.alelo.consumer.consumerpat.service.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsumerResponse {

	private Long id;
    
    private String name;
    
    private int documentNumber;
    
    private LocalDate birthDate;
    
    private ContactResponse contactResponse;
    
    private AddressResponse addressResponse;
    
    private CardsResponse cardsResponse;
}
