package br.com.alelo.consumer.consumerpat.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityInUseException;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.NonNullPropertyException;
import br.com.alelo.consumer.consumerpat.domain.respository.EstablishmentRepository;


@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	public List<Establishment> findAll(){
		return establishmentRepository.findAll();
	}
	
	public Establishment findById(Long id) {
		Establishment establishment = establishmentRepository
				.findById(id)
				.orElseThrow(() -> 
					new EntityNotFoundException(
							String.format("Estabelecimento de código %d não existe na base de dados!", id)));
		
		return establishment;
	}
	
	public Establishment save(Establishment establishment) {
		try{
			return establishmentRepository.save(establishment);
		}
		catch(DataIntegrityViolationException e) {
			throw new NonNullPropertyException(String.format("Verifique os dados inseridos no objeto."));
		}
	}
	
	public void delete(Long id) {
		try {
			establishmentRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Estabelecimento de código %d não pode ser removido, pois está em uso.", id));
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Estabelecimento de código %d não existe na base de dados!", id));
		}
	}

	
}
