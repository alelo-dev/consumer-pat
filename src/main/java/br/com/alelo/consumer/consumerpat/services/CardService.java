package br.com.alelo.consumer.consumerpat.services;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public Card setBalance(String cardNumber, Double value) {
        Card obj = repository.findByCardNumber(cardNumber);

        if(Objects.nonNull(obj)) {
            obj.setCardBalance(obj.getCardBalance() + value);
            return repository.save(obj);
        }
        throw new RuntimeException("Objeto nao encontrado");
    }
}
