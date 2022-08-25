package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.EstablishmentDto;
import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;

@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	
	public void save(EstablishmentDto establishmentDto) {
		Establishment establishment = new Establishment();
		BeanUtils.copyProperties(establishmentDto, establishment);
		establishmentRepository.save(establishment);		
	}
	
	public Establishment findById(Long establishmentId) {
		return establishmentRepository.findById(establishmentId)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Establishment com id %s não encontrado", establishmentId)));

	}

}
