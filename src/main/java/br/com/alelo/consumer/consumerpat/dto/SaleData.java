package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleData {
	
	private Establishment establishment;
	
	private CardConsumer cardConsumer;
	
	private SaleRequestDTO saleRequestDTO;

}
