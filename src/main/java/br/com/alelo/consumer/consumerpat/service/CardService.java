package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.payload.BuyPayload;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeMismatchException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
	
    private final CardRepository repository;

    private final ExtractRepository extractRepository;
	
	public void setBalance(String cardNumber, BigDecimal value) {
        final var card = repository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException(cardNumber));
        		
		card.add(value);
		final var savedCard = repository.save(card);
		log.info("Credit added to card: {}", savedCard);
    }

	 /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */
    public void buy(String cardNumber, BuyPayload payload) {
        final var card = repository.findById(cardNumber).orElseThrow(() -> new CardNotFoundException(cardNumber));
        
        if(card.getType() != payload.getEstablishmentType())
        	throw new CardTypeMismatchException(card.getType(), payload.getEstablishmentType());

        final var adjustedValue = payload.getEstablishmentType().adjustValue(payload.getValue()); 
        card.remove(adjustedValue);
        repository.save(card);
        
        final var extract = extractRepository.save(new Extract(payload.getEstablishmentName(), payload.getProductDescription(), cardNumber, adjustedValue));
        log.info("Extract from transaction: {}", extract);
    }

}
