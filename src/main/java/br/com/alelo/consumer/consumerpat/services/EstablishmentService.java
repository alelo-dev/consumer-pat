package br.com.alelo.consumer.consumerpat.services;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Establishment;

public interface EstablishmentService {

	Establishment save(Establishment establishment);
	
	Optional<Establishment> getEstablishmentById( long id);
	
}
