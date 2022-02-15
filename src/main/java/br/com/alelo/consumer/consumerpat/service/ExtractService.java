package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import br.com.alelo.consumer.consumerpat.exception.ApplicationException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {

	@Autowired
	private CardService cardService;	
	
	@Autowired
	private ExtractRepository extractRepository;
	
	public Extract save(Extract extract) {
		return extractRepository.save(extract);
	}
	
	@Transactional
	public Extract buy (ExtractDTO extractDTO) {
		
		Extract extract = extractDTO.tranformDTOToEntity();
		
		Card card = cardService.findCardByNumber(extract.getCardNumber());
		
		this.validateCard(extractDTO, card); 
		
		if (extractDTO.getEstablishmentType().equalsIgnoreCase((EstablishmentType.FOOD_CARD.name()))) {
			
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (extract.getValue() / 100) * 10;
			extract.setValue(extract.getValue() - cashback);

		} else if (extractDTO.getEstablishmentType().equalsIgnoreCase((EstablishmentType.FUEL_CARD.name()))) {
			
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (extract.getValue() / 100) * 35;
			extract.setValue(extract.getValue() + tax);
		}
		
		card.setBalance(card.getBalance() -  extract.getValue());
		cardService.save(card);		
		
		extract.setDateBuy(new Date());
		
		extract = extractRepository.save(extract);
		
		return extract;
	}
	
	
	private boolean validateCard(ExtractDTO extractDTO, Card card) {
		if (!card.getTipo().name()
				.equalsIgnoreCase((extractDTO.getEstablishmentType()))) {
			throw new ApplicationException("Este cartão não pertence ao tipo selecionado.");
		}
		return true;
	}
}
