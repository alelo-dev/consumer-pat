package br.com.alelo.consumer.consumerpat.services;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Purchase;

public interface PurchaseService {

	Purchase save(Purchase purchase);
	
	Optional<Purchase> getPurchaseById( long id);
}