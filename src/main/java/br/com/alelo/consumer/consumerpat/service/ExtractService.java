package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {

	@Autowired
	ExtractRepository extractRepository;
	
	@Transactional
	public void saveExtract(BuyDTO dto) {
		 Extract extract = new Extract(dto.getEstablishmentName(), 
				 dto.getProductDescription(), new Date(),dto.getCardNumber(),dto.getValue());
	        extractRepository.save(extract);
		
	}

}
