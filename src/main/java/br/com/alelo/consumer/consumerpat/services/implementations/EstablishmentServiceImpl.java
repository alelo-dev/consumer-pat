package br.com.alelo.consumer.consumerpat.services.implementations;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.services.EstablishmentService;


@Service
public class EstablishmentServiceImpl implements EstablishmentService {

	
	private static final Logger log = LoggerFactory.getLogger(EstablishmentServiceImpl.class);

	
    @Autowired
    EstablishmentRepository establishmentRepository;


	@Override
	public Establishment save(Establishment establishment) {

		log.info("Executing save()");
		
		return establishmentRepository.save( establishment );
	}


	@Override
	public Optional<Establishment> getEstablishmentById( long id ) {

		return establishmentRepository.findById( id );
	}

	
}