package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ConsumerRepository consumerRepository;


    public void setBalance(Integer cardNumber, Double value) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);


    }
}
