package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateReq;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.exception.CardIsExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;
    @Autowired
    private CardRepository cardRepository;

    public Consumer saveConsumer(ConsumerCreateReq consumer) throws Exception {
        Consumer consumer1 = consumer.toConsumer();

        Optional<Optional<Card>> cardOptional = consumer1.getCards()
                                        .stream()
                                        .map(card -> {
                                            return cardRepository.findByCardNumber(card.getCardNumber());
                                        }).findAny();

        if(cardOptional.get().isPresent()){
            throw new CardIsExistException("Card "+cardOptional.get().get().getCardNumber()+" is exist in system");
        }
        return repository.save(consumer1);
    }

    public List<Consumer> listAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

}
