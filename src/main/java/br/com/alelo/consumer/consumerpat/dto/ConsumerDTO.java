package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDTO {

	private Long id;
	private String name;
    private Integer documentNumber;
    private LocalDate birthDate;
    private List<AddressDTO> address;
    private List<CardDTO> cards;
    private List<ContactDTO> contacts;
    
    public static ConsumerDTO to(Consumer consumer) {
    	return new ConsumerDTO(
    			consumer.getId(), 
    			consumer.getName(), 
    			consumer.getDocumentNumber(), 
    			consumer.getBirthDate(), 
    			consumer.getAddress().stream().map(AddressDTO::to).collect(Collectors.toList()),
    			consumer.getCards().stream().map(CardDTO::to).collect(Collectors.toList()),
    			consumer.getContacts().stream().map(ContactDTO::to).collect(Collectors.toList())
			);
    }
    
    public Consumer toEntity() {
    	return new Consumer(
    				id,
    				name,
    				documentNumber, 
    				birthDate, 
    				address.stream().map(a -> a.toEntity()).collect(Collectors.toList()),
    				cards.stream().map(c -> c.toEntity()).collect(Collectors.toList()),
    				contacts.stream().map(c -> c.toEntity()).collect(Collectors.toList())
    			);
    }
}
