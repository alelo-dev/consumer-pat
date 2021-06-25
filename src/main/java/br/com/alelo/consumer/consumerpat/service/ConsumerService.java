package br.com.alelo.consumer.consumerpat.service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.model.EstablishementCards;
import br.com.alelo.consumer.consumerpat.model.FuelCards;
import br.com.alelo.consumer.consumerpat.model.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.model.entity.Address;
import br.com.alelo.consumer.consumerpat.model.entity.Cards;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.entity.Contacts;
import br.com.alelo.consumer.consumerpat.respository.CardsRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.execeptions.AuthorizationException;
import br.com.alelo.consumer.consumerpat.service.execeptions.ObjectNotFoundException;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	CardsRepository cardsRepository;

	@Autowired
	CardsService cardsService;

	public List<Consumer> listAllConsumers() {
//		List<Consumer> consumerList =repository.findAll();
//		List<ConsumerDTO> consumerDTO =new ArrayList<>();
//		
//		for (Consumer c :consumerList) {
//		c.getContacts().setConsumer(null);	
//		c.getAddress().forEach(a -> a.setConsumer(null));
//		c.getCards().forEach(a -> a.setConsumer(null));
//		//List<ConsumerDTO> consumerDTO = ;
//		List<ConsumerDTO> consumerDTO = consumer.stream().map(obj -> new ConsumerDTO(obj)).collect(Collectors.toList());
//		System.out.println(consumerList);
//		}
		return repository.findAll();

	}

	@Transactional
	public Consumer createConsumer(Consumer consumer) {		
		Consumer consumer2=consumer;
		consumer.getContacts().setConsumer(consumer2);
		consumer.getAddress().forEach(c -> c.setConsumer(consumer2));
		consumer.getCards().forEach(c -> c.setConsumer(consumer2));
		consumer = repository.save(consumer);
	

		return consumer;

	}

	@Transactional
	public void updateConsumer(ConsumerUpdateDTO consumerDTO) {
		Consumer newConsumer = repository.findById(consumerDTO.getId())
				.orElseThrow(() -> new ObjectNotFoundException("Object not found! id " + consumerDTO.getId()));

		Consumer consumer = fromDTO(consumerDTO);
		updateData(newConsumer, consumer);
		repository.save(newConsumer);

	}

	public void setBalance(BigInteger cardNumber, Double value) {
		Cards cards = cardsRepository.findByCardNumber(cardNumber);
		cards.setCardBalance(cards.getCardBalance() + value);
		cardsRepository.save(cards);

	}

	public void buy(BuyDTO dto) {

		Cards cards = cardsRepository.findByCardNumber(dto.getCardNumber());
	//	Cards EstablishementCards= new EstablishementCards();

		if (cards.getCardsType().getCod() == dto.getEstablishmentType()) {
			switch (cards.getCardsType().getCod()) {

			case 1: EstablishementCards food= new EstablishementCards(cards.getId(),cards.getConsumer(),cards.getCardNumber(),cards.getCardBalance(),cards.getCardsType());
				cardsService.buy(food, dto);
				break;

			case 2:
				cardsService.buy(cards, dto);
				break;

			case 3:FuelCards fuel =new FuelCards(cards.getId(),cards.getConsumer(),cards.getCardNumber(),cards.getCardBalance(),cards.getCardsType());
				cardsService.buy(fuel, dto);
				break;

			default:
				break;
			}

		} else {
			throw new AuthorizationException("invalid card");
		}

	}

	private void updateData(Consumer newConsumer, Consumer consumer) {
		newConsumer.setName(consumer.getName());
		newConsumer.setAddress(consumer.getAddress());
		newConsumer.setDocumentNumber(consumer.getDocumentNumber());
		newConsumer.setContacts(consumer.getContacts());
		newConsumer.setBirthDate(consumer.getBirthDate());
	}

	private Consumer fromDTO(ConsumerUpdateDTO obj) {
		Contacts contacts = new Contacts(obj.getContacts().getId(), obj.getContacts().getMobilePhoneNumber(),
				obj.getContacts().getResidencePhoneNumber(), obj.getContacts().getResidencePhoneNumber(),
				obj.getContacts().getEmail());
		List<Address> addressList = obj.getAddress().stream().map(a -> Address.from(a)).collect(Collectors.toList()); // Address
		Consumer consumer = new Consumer(obj.getId(), obj.getName(), obj.getDocumentNumber(), obj.getBirthDate(),
				contacts, addressList, null);

		return consumer;

	}

}
