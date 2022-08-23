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
	private CardRepository cardRepository;
	
	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
		
    @Autowired
    private MapConsumerDto mapConsumerDto;

    
	public void save(ConsumerDto consumerDto) {		
		consumerRepository.save(mapConsumerDto.create(consumerDto));
	}
	
	public Page<Consumer> findAll(int page, int size) {
		return consumerRepository.findAll(PageRequest.of(page, size));
	}
	public void update(Long id, ConsumerDto consumerDto) {
		Optional<Consumer> consumerOp = consumerRepository.findById(id);
		if(consumerOp.isPresent()) {
			Consumer consumer = consumerOp.get();
			mapConsumerDto.update(consumerDto, consumer);
			consumerRepository.save(consumer);				
		}else {
			//TODO lançar exception se não encontrar
		}
	}

	public void credit(Long cardNumber, double value) {
		Consumer consumer = consumerRepository.findByCardNumber(cardNumber);
		consumer.credit(cardNumber, value);
		consumerRepository.save(consumer);
	}
	
	public void buy(Long cardNumber, BuyDto buyDto) {
		//TODO lançar exception se não encontrar
		Card card = cardRepository.findByCardNumber(cardNumber);
		
		//TODO lançar exception se não encontrar
		Establishment establishment = establishmentRepository.getOne(buyDto.getEstablishmentId());
		
		//TODO lançar exception se o estabelecimento e o cartão não tiverem o mesmo tipo
		
		double debit = card.debit(buyDto.getValue());
		Lancamento lancamento = Lancamento.builder()
				.card(card)
				.dateBuy(new Date())
				.productDescription(buyDto.getProductDescription())
				.value(debit)
				.establishment(establishment)
				.build();	
		cardRepository.save(card);
		lancamentoRepository.save(lancamento);
	}
	
}
