package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;

public interface IEstablishmentService {
	
	public EstablishmentResponseDTO getEstablishmentResponse(Long id) throws Exception;
	
	public Establishment getEstablishment(Long id) throws Exception;

}
