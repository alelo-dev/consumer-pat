package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.CardNotAcceptedException;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.service.IExtractService;

@Service
public class ExtractService implements IExtractService{

	@Autowired
	ExtractRepository repository;

	@Autowired
	ICardService cardService;
	
	@Override
	public void buy(ExtractDTO extractDTO) {
		Extract extract = extractDTO.toEntity();
		validations(extract, cardService.findByNumber(extract.getCardNumber()));
		cardService.buyCardBalance(extract.getCardNumber(), extract.getValue());
		repository.save(extract);
	}

	/**
	 * Método responsável por realizar as validações antes do processamento da transação.
	 * 
	 * @param extract
	 * @param card
	 */
	private void validations(Extract extract, Card card) {
		if (!card.getType().name().equals(extract.getEstablishmentType().name())) {
			throw new CardNotAcceptedException(extract.getEstablishmentType().name(), card.getType().name());
		}
	}

}
