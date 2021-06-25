package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.model.Address;
import br.com.alelo.consumer.consumerpat.data.model.Card;
import br.com.alelo.consumer.consumerpat.data.model.Consumer;
import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import br.com.alelo.consumer.consumerpat.util.exception.InvalidOperationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.util.converter.CustomConsumerConverter.parseObject;

@Service
public class ConsumerService {

    ConsumerRepository repository;
    CardRepository cardRepository;
    AddressRepository addressRepository;

    ConsumerService(ConsumerRepository repository) {this.repository = repository;}

    public Page<Consumer> getAllConsumersList(Pageable pageable){
        return repository.findAll(pageable);
    }

    public ConsumerVO save(ConsumerVO vo) {
        Optional<Consumer> entity = repository.findById(vo.getId());
        return parseObject(entity.isPresent() ? update(entity.get(), vo) : register(vo));
    }

    private Consumer register(ConsumerVO vo) {
        Consumer consumer = saveConsumer(new Consumer(), vo);

        Address address = new Address(); address.setConsumerId(consumer.getId());
        saveAddress(address, vo);

        registerCards(consumer, vo);
        return consumer;
    }

    private void registerCards(Consumer consumer, ConsumerVO vo) {
        Card food = new Card(consumer, TypeCardsEnum.FOOD, vo.getFoodCardBalance(), vo.getFoodCardNumber());
        cardRepository.save(food);

        Card drugstore = new Card(consumer, TypeCardsEnum.DRUGSTORE, vo.getDrugstoreCardBalance(), vo.getDrugstoreCardNumber());
        cardRepository.save(drugstore);

        Card fuel = new Card(consumer, TypeCardsEnum.FUEL, vo.getFuelCardBalance(), vo.getFuelCardNumber());
        cardRepository.save(fuel);
    }

    private Consumer update(Consumer entity, ConsumerVO vo) {
        saveAddress(entity.getAddress(), vo);
        updateCards(entity.getCards(), vo);
        return saveConsumer(entity, vo);
    }

    private void updateCards(List<Card> cards, ConsumerVO vo) {
        cards.forEach(card -> {
            switch (card.getType()) {
                case FOOD: card.setNumber(vo.getFoodCardNumber());
                    break;
                case FUEL:card.setNumber(vo.getFuelCardNumber());
                    break;
                case DRUGSTORE: card.setNumber(vo.getDrugstoreCardNumber());
            }
            cardRepository.save(card);
        });
    }

    private void saveAddress(Address address, ConsumerVO vo) {
        address.setCity(vo.getCity());
        address.setCountry(vo.getCountry());
        address.setNumber(vo.getNumber());
        address.setStreet(vo.getStreet());
        address.setPostalCode(vo.getPostalCode());
        addressRepository.save(address);
    }

    private Consumer saveConsumer(Consumer entity, ConsumerVO vo) {
        entity.setBirthDate(vo.getBirthDate());
        entity.setEmail(vo.getEmail());
        entity.setDocumentNumber(vo.getDocumentNumber());
        entity.setPhoneNumber(vo.getPhoneNumber());
        entity.setResidencePhoneNumber(vo.getResidencePhoneNumber());
        entity.setMobilePhoneNumber(vo.getMobilePhoneNumber());
        return repository.save(entity);
    }

    public void incrementBalance(Long cardNumber, BigDecimal value) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new InvalidOperationException("No records found for this card number: "+cardNumber));

        card.setBalance(card.getBalance().add(value));
        cardRepository.save(card);
    }
}

