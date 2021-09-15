package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

/**
 * 
 * @author Alexandre Ventecinco
 * @since  15/09/2021
 * 
 * @version 1.0
 * 
 * Classe de serviço para inserção do Extract
 *
 */

@Service
public class ExtractService {
	
	@Autowired
	private ExtractRepository repository ;

	@Transactional
	public void save( Extract extract ) {
		this.repository.save( extract ) ;
	}

}
