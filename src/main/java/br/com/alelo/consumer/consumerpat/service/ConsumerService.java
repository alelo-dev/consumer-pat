package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.MapConsumerDto;
import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.error.IncompatibleType;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.model.Lancamento;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.LancamentoRepository;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private CardService cardService;
	
	private EstablishmentService establishmentService;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private MapConsumerDto mapConsumerDto;

	public void save(ConsumerDto consumerDto) {
		consumerRepository.save(mapConsumerDto.create(consumerDto));
	}

	public Page<Consumer> findAll(int page, int size) {
		return consumerRepository.findAll(PageRequest.of(page - 1, size));
	}

	public void update(Long id, ConsumerDto consumerDto) {
		Optional<Consumer> consumerOp = consumerRepository.findById(id);
		if (consumerOp.isPresent()) {
			Consumer consumer = consumerOp.get();
			mapConsumerDto.update(consumerDto, consumer);
			consumerRepository.save(consumer);
		} else {
			throw new EntityNotFoundException(String.format("Consumer com id %s não encontrado", id));
		}
	}

	public void credit(Long cardNumber, double value) {
		Consumer consumer = consumerRepository.findByCardNumber(cardNumber);
		consumer.credit(cardNumber, value);
		consumerRepository.save(consumer);
	}

	public void buy(Long cardNumber, BuyDto buyDto) {
		
		Card card = cardService.findByCardNumber(cardNumber);	
		Establishment establishment = establishmentService.findById(buyDto.getEstablishmentId());

		if (establishment.getType() != card.getType()) {
			throw new IncompatibleType(
					String.format("O tipo do estabelecimento é %s, incompatível com o tipo do cartão que é %s",
							establishment.getType(), card.getType()));
		}
		double debit = card.debit(buyDto.getValue());
		Lancamento lancamento = Lancamento.builder().card(card).dateBuy(new Date())
				.productDescription(buyDto.getProductDescription()).value(debit).establishment(establishment).build();
		cardService.save(card);
		lancamentoRepository.save(lancamento);
	}

}
