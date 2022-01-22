package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.OperationTypeEnum;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    final private CardRepository cardRepository;
    final private ExtractRepository extractRepository;

    @Override
    @Transactional
    public void setBalance(String cardNumber, Double value) {
        Card card = cardRepository.findCardByNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        extractRepository.save(
                Extract.builder()
                        .cardNumber(card.getNumber())
                        .operationTYpe(OperationTypeEnum.CREDIT.getValue())
                        .dateOperation(LocalDateTime.now())
                        .value(value)
                        .build());

        card.setBalance(card.getBalance() + value);
        cardRepository.save(card);
    }
}
