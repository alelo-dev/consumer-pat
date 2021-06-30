package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public interface Card {

	/*
	 * O valores só podem ser debitados dos cartões com os tipos correspondentes ao
	 * tipo do estabelecimento da compra. Exemplo: Se a compra é em um
	 * estabelecimeto de Alimentação(food) então o valor só pode ser debitado do
	 * cartão e alimentação
	 *
	 * Tipos de estabelcimentos 1 - Alimentação (food) 2 - Farmácia (DrugStore) 3 -
	 * Posto de combustivel (Fuel)
	 */
	Consumer buy(ExtractDTO param, Consumer consumer, ConsumerRepository repository);

}
