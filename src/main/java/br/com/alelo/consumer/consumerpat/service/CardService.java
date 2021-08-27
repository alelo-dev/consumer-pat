package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.InvalidEstablishmentException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
	
    private final CardRepository repository;

    private final ExtractRepository extractRepository;
	
	public void setBalance(String cardNumber, BigDecimal value) {
        final var card = repository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException(cardNumber));
        		
		card.add(value);
		repository.save(card);
        		
    }

	 /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    public void buy(String cardNumber, BuyDTO buyDTO) {
        final var card = repository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException(cardNumber));
        
        if(card.getType() != buyDTO.getEstablishmentType())
        	throw new InvalidEstablishmentException();

        final var adjustedValue = buyDTO.getEstablishmentType().adjustValue(buyDTO.getValue()); 
        card.remove(adjustedValue);
        repository.save(card);
        
        extractRepository.save(new Extract(buyDTO.getEstablishmentName(), buyDTO.getProductDescription(), new Date(), 
        		cardNumber, adjustedValue));
    }

}
