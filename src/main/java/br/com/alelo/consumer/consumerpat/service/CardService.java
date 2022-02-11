package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyInfoDto;
import br.com.alelo.consumer.consumerpat.dto.CardBalanceDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.EstablishmentType;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@Service
public class CardService {
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private ExtractRepository extractRepository;


	/*
	 * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele precisa indenficar
	 * qual o cartão correto a ser recarregado, para isso deve usar o número do cartão(cardNumber)
	 * fornecido.
	 */
	public void setBalance(CardBalanceDto cardBalance) {
		Card card = this.cardRepository.findByNumber(cardBalance.getCardNumber());

		if (card != null) {
			card.setBalance(card.getBalance() + cardBalance.getValue());
			this.cardRepository.save(card);
		}
	}

	public void buy(BuyInfoDto buyInfo) {
		Card card = this.cardRepository.findByNumber(buyInfo.getCardNumber());
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        */
		double value = buyInfo.getValue();
		if (card.getType().equals(buyInfo.getEstablishmentType())) {
	        if (card.getType() == EstablishmentType.FOOD) {
	            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	            Double cashback  = (value / 100) * 10;
	            value = value - cashback;
	        }else if (card.getType() == EstablishmentType.FUEL) {
	            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	            Double tax  = (value / 100) * 35;
	            value = value + tax;            
	        }
	        
	        card.setBalance(card.getBalance() - value);
            this.cardRepository.save(card);
            
            Extract extract = new Extract(buyInfo.getEstablishmentName(), buyInfo.getProductDescription(), new Date(), buyInfo.getCardNumber(), value);
            this.extractRepository.save(extract);
		}        
	}
}
