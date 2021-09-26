package br.com.alelo.consumer.consumerpat.converter;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;


public class EstablishmentConverter {
	
public static EstablishmentResponseDTO toDTO(Establishment establishment) {
		
		if(establishment != null) {
			return EstablishmentResponseDTO.builder().id(establishment.getId()).
			name(establishment.getName()).
			description(establishment.getDecription()).build();
		}
		
		return null;
	}

}
