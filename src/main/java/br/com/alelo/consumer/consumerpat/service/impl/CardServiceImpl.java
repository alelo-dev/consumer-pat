package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.dto.SetBalanceDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.entity.Extract;
import br.com.alelo.consumer.consumerpat.model.enumeration.TypeCard;
import br.com.alelo.consumer.consumerpat.model.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
	
	@Autowired
	private final CardRepository cardRepository;
	
	@Override
	public Card setBalance(SetBalanceDTO setBalanceDTO) {
		Optional<Card> OptionalCard = cardRepository.findById(setBalanceDTO.getCardNumber());
		
		if(OptionalCard.isPresent()) {
			Card card = OptionalCard.get();
			card.setBalance(card.getBalance() + setBalanceDTO.getValue());
			return cardRepository.save(card);
		}
		
		return null;
	}

	@Override
	public List<Card> listAllCards() {
		return cardRepository.findAll();
	}

	@Override
	public Card createCard(Card card) {
		return cardRepository.save(card);
	}

	@Override
	public Card updateCard(Card card) {
		return cardRepository.save(card);
	}
	
	@Override
	public void buy(BuyDTO buyDTO) {
	  /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
	  *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
	  *
	  * Tipos de estabelcimentos
	  * 1 - Alimentação (food)
	  * 2 - Farmácia (DrugStore)
	  * 3 - Posto de combustivel (Fuel)
	  *       */
		
		Optional<Card> optionalCard = cardRepository.findById(buyDTO.getCardNumber());
		Card card = optionalCard.get();
		
		if(card != null) {
			// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
			
			if(buyDTO.getEstablishmentType() == 1 && card.getTypecard().equals(TypeCard.FOOD)) {
				Double cashback  = (buyDTO.getValue() / 100) * 10;
	            Double value = buyDTO.getValue() - cashback;
	            
	            card.setBalance(card.getBalance() - value);
			}else if(buyDTO.getEstablishmentType() == 2 && card.getTypecard().equals(TypeCard.DRUGSTORE)) {
				card.setBalance(card.getBalance() - buyDTO.getValue());
			}else {
				// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	            Double tax  = (buyDTO.getValue() / 100) * 35;
	            Double value = buyDTO.getValue() + tax;
	            
	            card.setBalance(card.getBalance() - value);
			}
			
			List<Extract> extracts = card.getExtracts();
			extracts.add(new Extract(buyDTO.getEstablishmentName(), buyDTO.getProductDescription() , new Date(), buyDTO.getCardNumber(), buyDTO.getValue()));
			card.setExtracts(extracts);
			cardRepository.save(card);
		}
		
	}

}
