package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.entity.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDTO {

	    private String name;
	    private EstablishmentType type;
	  	private Address address;
	
}
