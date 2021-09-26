package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.SaleData;
import br.com.alelo.consumer.consumerpat.dto.SaleRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.service.IEstablishmentService;
import br.com.alelo.consumer.consumerpat.service.ISaleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	ISaleService saleService;
	
	@Autowired
	ICardService cardService;
	
	@Autowired
	IEstablishmentService establishmentService;

	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping(value = "/sell", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void sell( final @RequestBody SaleRequestDTO saleDTO) throws Exception {
		
		CardConsumer cardConsumer = cardService.findByNumber(saleDTO.getCardNumber());
		Establishment establishment = establishmentService.getEstablishment(saleDTO.getEstablishmentId());
		
		SaleData saleData = SaleData.builder().saleRequestDTO(saleDTO).cardConsumer(cardConsumer).establishment(establishment).build();
		
		saleService.sell(saleData);
	}


}
