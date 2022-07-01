package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class ExtractService {
	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	public List<Extract> listAllExtracts() {
		return extractRepository.getAllExtractList();
	}

	public String buy(int establishmentType, Extract extract) {
		Consumer consumer = null;
		boolean buy = false;
		/*
		 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
		 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
		 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
		 * cartão e alimentação
		 *
		 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
		 * Posto de combustivel (Fuel)
		 */
		if (establishmentType == 1) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			Double cashback = (extract.getValue() / 100) * 10;
			extract.setValue(extract.getValue() - cashback);

			consumer = consumerRepository.findByFoodCardNumber(extract.getCardNumber());
			double finalBalance = consumer.getFoodCardBalance() - extract.getValue();
			if(finalBalance >= 0.0) {
				consumer.setFoodCardBalance(finalBalance);
				consumerRepository.save(consumer);
				buy = true;
			}
			

		} else if (establishmentType == 2) {
			consumer = consumerRepository.findByDrugstoreNumber(extract.getCardNumber());
			double finalBalance = consumer.getDrugstoreCardBalance() - extract.getValue();
			if(finalBalance >= 0.0) {
				consumer.setDrugstoreCardBalance(finalBalance);
				consumerRepository.save(consumer);
				buy = true;
			}

		} else {
			// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
			Double tax = (extract.getValue() / 100) * 35;
			extract.setValue(extract.getValue() + tax);

			consumer = consumerRepository.findByFuelCardNumber(extract.getCardNumber());
			double finalBalance = consumer.getFuelCardBalance() - extract.getValue();
			if(finalBalance >= 0.0) {
				consumer.setFuelCardBalance(finalBalance);
				consumerRepository.save(consumer);
				buy = true;
			}
		}
		
		if(buy) {
			Extract extractSave = new Extract(extract.getEstablishmentNameId(), extract.getEstablishmentName(),
					extract.getProductDescription(), new Date(), extract.getCardNumber(), extract.getValue());
			extractRepository.save(extractSave);
			return "Compra feita! Saldo do cartao atualizado com sucesso.";
		}else {
			return "Saldo insuficiente para realizar a compra!";
		}
	}
}
