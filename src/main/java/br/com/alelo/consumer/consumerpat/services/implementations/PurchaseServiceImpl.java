package br.com.alelo.consumer.consumerpat.services.implementations;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.respository.PurchaseRepository;
import br.com.alelo.consumer.consumerpat.services.PurchaseService;


@Service
public class PurchaseServiceImpl implements PurchaseService {

	
	private static final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	
    @Autowired
    PurchaseRepository purchaseRepository;


	@Override
	public Purchase save(Purchase purchase) {

		log.info("Executing save()");
		
		return purchaseRepository.save( purchase );
	}


	@Override
	public Optional<Purchase> getPurchaseById( long id ) {
		
		log.info("Getting consumer with id: ", id );
		
		return purchaseRepository.findById( id );
	}
	
}