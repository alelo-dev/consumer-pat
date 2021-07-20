package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.validation.CardValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private CardRepository repository;

    private CardValidator validator;

    @Override
    public Card updateBalance(final Long cardNumber, final BigDecimal value) {

        var card = validator.validateCard(cardNumber, value);
        card.setBalance(card.getBalance().add(value));

        try {
            return repository.save(card);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @Override
    public Card buy(final BuyDTO dto) {

        var card = validator.validateCard(dto.getCardNumber(), dto.getValue());
        var newBalance = validator.getNewBalance(card, dto.getEstablishmentType(),
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
