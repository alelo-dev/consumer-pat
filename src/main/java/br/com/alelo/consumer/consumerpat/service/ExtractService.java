package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.respository.filter.BuyFilter;

@Component
public class ExtractService {

	@Autowired
	private ExtractRepository extractRepository;

	public void saveExtract(BuyFilter buyFilter, int id, int establishmentNameId, Double value) {

		Extract extract = new Extract(buyFilter.getEstablishmentName(), establishmentNameId,
				buyFilter.getProductDescription(), new Date(), buyFilter.getCardNumber(), value);

		extractRepository.save(extract);

	}
}
