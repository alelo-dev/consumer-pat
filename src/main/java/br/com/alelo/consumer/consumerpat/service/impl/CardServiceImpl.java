package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.OperationTypeEnum;
import br.com.alelo.consumer.consumerpat.exceptions.CardAndEstablishmentTypeInvalidException;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.enums.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.alelo.consumer.consumerpat.enums.CardTypeEnum.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ExtractRepository extractRepository;

    @Override
    @Transactional
    public void setBalance(String cardNumber, Double value) {
        Card card = cardRepository.findCardByNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        extractRepository.save(
                Extract.builder()
                        .cardNumber(card.getNumber())
                        .operationType(OperationTypeEnum.CREDIT.getValue())
                        .dateOperation(LocalDateTime.now())
                        .value(value)
                        .build());

        card.setBalance(card.getBalance() + value);
        cardRepository.save(card);
    }

    @Override
    public void buy(Integer establishmentType, String establishmentName, String cardNumber, String productDescription, Double value) {
        Card card = cardRepository.findCardByNumber(cardNumber)
                .orElseThrow(CardNotFoundException::new);

        if (card.getCardType().equals(establishmentType)) {
            switch (getEnum(card.getCardType())) {
                case FOOD:
                    card.setBalance(card.getBalance() - (value - (value * 0.1)));
                    cardRepository.save(card);
                    break;
                case DRUGSTORE:
                    card.setBalance(card.getBalance() - value);
                    cardRepository.save(card);
                    break;
                case FUEL:
                    card.setBalance(card.getBalance() - (value + (value * 0.35)));
                    cardRepository.save(card);
                    break;
                default:
                    throw new CardTypeNotFoundException();
            }

            extractRepository.save(Extract.builder()
                    .value(value)
                    .dateOperation(LocalDateTime.now())
                    .operationType(OperationTypeEnum.DEBIT.getValue())
                    .cardNumber(card.getNumber())
                    .establishmentName(establishmentName)
                    .establishmentType(establishmentType)
                    .productDescription(productDescription)
                    .build());
        } else {
            throw new CardAndEstablishmentTypeInvalidException();
        }
    }
}
