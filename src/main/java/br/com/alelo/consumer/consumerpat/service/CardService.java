package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.domain.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class CardService {

    private final CardRepository repository;
    private final CardMapper mapper;

    @Autowired
    public CardService(CardRepository cardRepository, CardMapper mapper) {
        this.repository = cardRepository;
        this.mapper = mapper;
    }

    public CardDTO findByCardNumber(Integer cardNumber) {
        var card = repository.findByCardNumber(cardNumber);
        if(isNull(card)){
            throw  new EntityNotFoundException("Não foi encontrado um Cartão para o número informado");
        }
        return mapper.entityToDTO(card);
    }

    @Transactional
    public CardCreateResponseDTO save(CardDTO dto) throws BusinessException {

        this.validateCarNumberdExistis(dto.getCardNumber());
        var card = repository.save(mapper.dtoToEntity(dto));

        return mapper.newEntityToDTO(card);
    }

    @Transactional
    public Card debtByCard(Card card, BigDecimal value) throws BusinessException {

        this.validateCardBalance(card,value);
        card.setBalanceValue(card.getBalanceValue().subtract(value));
        return repository.save(card);
    }

    @Transactional
    public CardDTO credit(int cardNumber, BigDecimal value) {

        var card = repository.findByCardNumber(cardNumber);

        if (Objects.nonNull(card)) {
            creditByCard(card, value);
        }

        return mapper.entityToDTO(card);
    }


    public Card creditByCard(Card card, BigDecimal value) {
        card.setBalanceValue(card.getBalanceValue().add(value));
        return repository.save(card);

    }

    public void validateCardBalance(Card card, BigDecimal debitValue) throws BusinessException {
        if(card.getBalanceValue().compareTo(debitValue) < 0){
            throw new BusinessException("Não existe Crédito suficiente para transação");
        }
    }

    private void validateCarNumberdExistis(Integer cardNumber) throws BusinessException {
        var card = repository.findByCardNumber(cardNumber);
        if(nonNull(card)){
            throw  new BusinessException("Já existe um número de cartão registrado para o número informado");
        }
    }


}
