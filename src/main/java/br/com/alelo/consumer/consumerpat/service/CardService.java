package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.util.CardValidation;
import br.com.alelo.consumer.consumerpat.util.Constants;
import br.com.alelo.consumer.consumerpat.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ExtractService extractService;
    private final TransactionOperationStrategy transactionOperationStrategy;

    @Transactional
    public void increaseCardBalance(IncreaseCardBalanceDTO dto) {
        ObjectUtils.shouldNotBeNull(dto.getValue(), Constants.VALUE_FIELD);
        CardValidation.shouldHaveNumber(dto.getNumber());

        // Execute
        Card cardDB = findCardByNumber(dto.getNumber());
        cardDB.setBalance(Optional.ofNullable(cardDB.getBalance())
                .orElse(BigDecimal.ZERO)
                .add(dto.getValue())
        );
        cardRepository.save(cardDB);
    }

    public Card findCardByNumber(String number) {
        return cardRepository.findOneByNumber(number)
                .orElseThrow(() -> new NotFoundException(
                        String.format(Constants.CARD_NUMBER_NOT_FOUND, number),
                        Constants.CARD_NOT_FOUND));
    }

    @Transactional
    public void process(TransactionDTO dto) {
        ObjectUtils.shouldNotBeNull(dto.getEstablishmentType(), Constants.ESTABLISHMENT_TYPE_FIELD);
        ObjectUtils.shouldNotBeNull(dto.getValue(), Constants.VALUE_FIELD);
        CardValidation.shouldHaveNumber(dto.getCardNumber());

        Card card = findCardByNumber(dto.getCardNumber());
        CardType type = CardType.fromCode(dto.getEstablishmentType());

        shouldMatchTypes(card, type);
        BigDecimal value = transactionOperationStrategy.calculate(dto.getValue(), type);
        card.setBalance(card.getBalance().subtract(value));

        card = cardRepository.save(card);
        extractService.create(dto, card, value);
    }

    private void shouldMatchTypes(Card card, CardType type) {
        Optional.ofNullable(card.getTypes())
                .filter(it -> it.contains(type))
                .orElseThrow(() -> new ValidationException(Constants.ESTABLISHMENT_TYPE_IS_NOT_SUPPORTED_BY_THE_RELATED_CARD));
    }
}
