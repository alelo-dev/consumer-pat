package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.dto.v2.BuyDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.CardType;
import br.com.alelo.consumer.consumerpat.domain.entity.enums.ErrorMessage;
import br.com.alelo.consumer.consumerpat.domain.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.EstablishmentInvalidException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.domain.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ExtractService extractService;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void setBalance(int cardNumber, double value) throws InsuficientBalanceException, CardNotFoundException {

        //valida as informacoes passadas
        var cardActual = validateCardInformations(cardNumber, value);

        //adiciona o valor ao saldo atual do cartao
        cardActual.setCardBalance(cardActual.getCardBalance().add(BigDecimal.valueOf(value)));

        //persiste o dado
        cardRepository.save(cardActual);
    }

    @Override
    public ExtractEntity buy(BuyDTO buyDTO) throws CardNotFoundException, EstablishmentInvalidException, InsuficientBalanceException {
        Optional<CardEntity> cardEntity = cardRepository.findByNumber(buyDTO.getCardNumber());

        if(cardEntity.isEmpty()){
            throw new CardNotFoundException(ErrorMessage.CARTAO_INEXISTENTE);
        }

        buyDTO.setValue(calculateFinalValue(cardEntity.get().getType(), buyDTO.getValue()));

        if(cardEntity.get().getType().getCardId() != buyDTO.getEstablishmentType())
            throw new EstablishmentInvalidException(ErrorMessage.ESTABELECIMENTO_NAO_PERMITIDO_PARA_CARTAO);

        if(cardEntity.get().getCardBalance().compareTo(BigDecimal.valueOf(buyDTO.getValue())) < 0)
            throw new InsuficientBalanceException(ErrorMessage.SALDO_INSUFICIENTE);

        cardEntity.get().setCardBalance(cardEntity.get().getCardBalance().subtract(BigDecimal.valueOf(buyDTO.getValue())));
        cardRepository.save(cardEntity.get());

        return extractService.save(buyDTO);
    }

    /**
     * Realiza validação lógica dos dados do cartao
     *
     * @param cardNumber
     * @param value
     * @throws InsuficientBalanceException
     * @throws CardNotFoundException
     */
    private CardEntity validateCardInformations(int cardNumber, double value) throws InsuficientBalanceException, CardNotFoundException {
        if(value <= 0){
            throw new InsuficientBalanceException(ErrorMessage.VALOR_MENOR_OU_IGUAL_ZERO);
        }

        Optional<CardEntity> optionalCard = cardRepository.findByNumber(cardNumber);
        if(optionalCard.isEmpty()){
            throw new CardNotFoundException(ErrorMessage.CARTAO_INEXISTENTE);
        }
        return optionalCard.get();
    }

    private double calculateFinalValue(CardType cardType, double value){
        switch (cardType){
            case FOOD:
                value = value - ((value / 100) * 10);
                break;
            case FUEL:
                value = value + ((value / 100) * 35);
                break;
        }
        return value;
    }

}
