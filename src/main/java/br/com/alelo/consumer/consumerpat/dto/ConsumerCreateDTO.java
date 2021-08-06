package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerCreateDTO {
	
    private String name;
    private Integer documentNumber;
    private LocalDate birthDate;
    private List<AddressCreateDTO> address;
    private List<ContactCreateDTO> contacts;
    private List<CardCreateDTO> cards;
    
    public Consumer toEntity() {
    	return new Consumer(
    				null,
    				name,
    				documentNumber, 
    				birthDate, 
    				address.stream().map(a -> a.toEntity()).collect(Collectors.toList()),
    				cards.stream().map(c -> c.toEntity()).collect(Collectors.toList()),
    				contacts.stream().map(c -> c.toEntity()).collect(Collectors.toList())
    			);
    }
}
