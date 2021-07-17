package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.repository.ICardRepository;
import br.com.alelo.consumer.consumerpat.service.validator.CardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final ICardRepository repository;

    private final CardValidator validator;

    @Override
    public Card updateBalance(final Long cardNumber, final BigDecimal value) {

        var card = validator.validCard(cardNumber, value);
        card.setBalance(card.getBalance().add(value));

        try {
            return repository.save(card);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @Override
    public Card buy(final RequestBuyDTO dto) {

        var card = validator.validCard(dto.getCardNumber(), dto.getValue());
        var newBalance = validator.getAdditionForBalanceByType(card, dto.getEstablishmentType(),
                dto.getValue());
        card.setBalance(newBalance);
        dto.setNewValue(newBalance);
        try {
            return repository.save(card);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
