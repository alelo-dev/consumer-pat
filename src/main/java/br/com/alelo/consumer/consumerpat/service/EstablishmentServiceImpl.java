package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.converter.EstablishmentConverter;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;

@Service
public class EstablishmentServiceImpl implements IEstablishmentService{
	
	@Autowired
	private EstablishmentRepository establishentRepository;

	@Override
	public EstablishmentResponseDTO getEstablishmentResponse(Long id) throws Exception {
		
        final Establishment establishment = establishentRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Establishment not found"));
		return EstablishmentConverter.toDTO(establishment);
	}
	
	@Override
	public Establishment getEstablishment(Long id) throws Exception {
		
        final Establishment establishment = establishentRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Establishment not found"));
		return establishment;
	}

}
