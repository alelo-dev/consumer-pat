package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.model.Extract;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import br.com.alelo.consumer.consumerpat.util.exception.InvalidOperationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ExtractService {

    ExtractRepository repository;
    CardRepository cardRepository;

    public ExtractService(ExtractRepository extractRepository, CardRepository cardRepository) {
        this.repository = extractRepository;
        this.cardRepository = cardRepository;
    }

    public Extract buy(Integer establishmentType, Long cardNumber, BigDecimal value, String establishmentName,
                       String productDescription){

        var extract = new Extract();
        var card = cardRepository.findByFoodCardNumberAndType(cardNumber, establishmentType)
                .orElseThrow(() -> new InvalidOperationException("Card not found Establishment Type"));

        var discount = BigDecimal.valueOf(TypeCardsEnum.getTaxByValue(establishmentType)).multiply(value);
        value = value.add(discount);

        if(isSuficientBalance(card.getBalance(), value)) {
            card.setBalance(card.getBalance().subtract(value));

            cardRepository.save(card);

            extract = new Extract(establishmentName, productDescription, new Date(), card.getId(), value);
            repository.save(extract);
        }

        return extract;
    }

    private boolean isSuficientBalance(BigDecimal balance, BigDecimal value){
        if (balance.compareTo(value) < 0)
            throw new InvalidOperationException("Insufficient balance.");
        return Boolean.TRUE;
    }
}
