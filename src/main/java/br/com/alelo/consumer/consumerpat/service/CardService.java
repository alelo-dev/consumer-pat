package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
public class CardService {

    private final CardRepository repository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.repository = cardRepository;
    }

    public Card findByCardNumber(Integer cardNumber) {
        return repository.findByCardNumber(cardNumber);
    }

    @Transactional
    public CardCreateResponseDTO save(CardDTO dto) throws BusinessException {

        this.validateCarNumberdExistis(dto.getNumber());
        var card = CardMapper.dtoToEntity(dto);
        card = repository.save(card);

        return CardMapper.newEntityToDTO(card);
    }


    @Transactional
    public Card debtByCard(Card card, BigDecimal value) {

        card.setBalanceValue(card.getBalanceValue().subtract(value));
        card = repository.save(card);
        return card;
    }

    @Transactional
    public TransactionDTO credit(int cardNumber, BigDecimal value) {

        var card = repository.findByCardNumber(cardNumber);

        if (Objects.nonNull(card)) {
            creditByCard(card, value);
        }

        return TransactionDTO.
                builder().build();
    }


    public Card creditByCard(Card card, BigDecimal value) {
        card.setBalanceValue(card.getBalanceValue().add(value));
        return repository.save(card);

    }

    private void validateCarNumberdExistis(Integer cardNumber) throws BusinessException {
        var card = repository.findByCardNumber(cardNumber);
        if(nonNull(card)){
            throw  new BusinessException("Já existe um número de cartão registrado para o número informado");
        }
    }


}
