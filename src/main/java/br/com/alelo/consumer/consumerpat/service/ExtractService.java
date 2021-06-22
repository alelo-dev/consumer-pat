package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.model.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ExtractService {

    ExtractRepository repository;
    CardRepository cardRepository;

    public void buy(Integer establishmentType, Integer cardNumber, BigDecimal value, String establishmentName, String productDescription){

        var card = cardRepository.findByFoodCardNumberAndType(cardNumber, establishmentType)
                .orElseThrow(() -> new IllegalArgumentException("Card not found Establishment Type"));

        var discount = BigDecimal.valueOf(TypeCardsEnum.getTaxByValue(establishmentType)).multiply(value);
        value = value.add(discount);
        card.setBalance(card.getBalance().subtract(value));

        cardRepository.save(card);

        Extract extract = new Extract(establishmentName, productDescription, new Date(), card.getId(), value);
        repository.save(extract);
    }
}
