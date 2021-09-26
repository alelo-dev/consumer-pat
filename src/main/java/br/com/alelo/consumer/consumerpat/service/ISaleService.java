package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.SaleData;
import br.com.alelo.consumer.consumerpat.dto.SaleRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;

public interface ISaleService {

	public void sell(SaleData saleData);
	
	
}
