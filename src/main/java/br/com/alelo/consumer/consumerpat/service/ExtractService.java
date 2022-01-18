package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {

	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ExtractRepository extractRepository;
	
	public Extract save(Extract extract) {
		return extractRepository.save(extract);
	}
	
	@Transactional
	public Extract buy (ExtractDTO extractDTO) {
		
		Extract extract = extractDTO.tranformDTOToEntity();
		
		Consumer consumer = null;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */

		if (extractDTO.getEstablishmentType() == EstablishmentType.FOOD_CARD.getId()) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (extract.getValue() / 100) * 10;
			extract.setValue(extract.getValue() - cashback);

			consumer = consumerService.findByFoodCardNumber(extract.getCardNumber());
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - extract.getValue());
			consumerService.save(consumer);

		} else if (extractDTO.getEstablishmentType() == EstablishmentType.DRUGSTORE_CARD.getId()) {
			consumer = consumerService.findByDrugstoreNumber(extract.getCardNumber());
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - extract.getValue());
			consumerService.save(consumer);

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (extract.getValue() / 100) * 35;
			extract.setValue(extract.getValue() + tax);

			consumer = consumerService.findByFuelCardNumber(extract.getCardNumber());
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - extract.getValue());
			consumerService.save(consumer);
		}

		extract.setDateBuy(new Date());
		
		extract = extractRepository.save(extract);
		
		return extract;
	}
}
