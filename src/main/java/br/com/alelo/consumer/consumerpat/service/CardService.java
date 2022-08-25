package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.error.IncompatibleType;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.model.Lancamento;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.LancamentoRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private EstablishmentService establishmentService;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	
	public void save(Card card) {
		cardRepository.save(card);
	}
	
	public Card findByCardNumber(Long cardNumber) {
		return Optional.ofNullable(cardRepository.findByCardNumber(cardNumber))//
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Card com cardNumber %s não encontrado", cardNumber)));
	}

	public void assign(Long cardNumber, double value) {
		Card card = findByCardNumber(cardNumber);
		card.setBalance(value);
		cardRepository.save(card);		
	}

	public void buy(Long cardNumber, BuyDto buyDto) {
		
		Card card = findByCardNumber(cardNumber);	
		Establishment establishment = establishmentService.findById(buyDto.getEstablishmentId());

		if (establishment.getType() != card.getType()) {
			throw new IncompatibleType(
					String.format("O tipo do estabelecimento é %s, incompatível com o tipo do cartão que é %s",
							establishment.getType(), card.getType()));
		}
		double debit = card.debit(buyDto.getValue());
		Lancamento lancamento = Lancamento.builder().card(card).dateBuy(new Date())
				.productDescription(buyDto.getProductDescription()).value(debit).establishment(establishment).build();
		cardRepository.save(card);
		lancamentoRepository.save(lancamento);
	}

}
