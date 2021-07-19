package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.entity.dto.BuyCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.exception.InvalidTransactionException;
import br.com.alelo.consumer.consumerpat.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

import static br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType.toEnum;

@Service
public class TransactionService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    public Card findCardByCardNumber(String cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Card")));

    }

    public BigDecimal addBalance(CardBalanceDTO cardBalanceDTO) {

        Card card = cardRepository.findCardByCardNumber(cardBalanceDTO.getCardNumber())
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Card")));
        card.setCardBalance(card.getCardBalance().add(cardBalanceDTO.getValue()));
        cardRepository.save(card);
        return card.getCardBalance();
    }

    public void processBuy(BuyCardBalanceDTO buyCardBalanceDTO) {

        Card card = this.findCardByCardNumber(buyCardBalanceDTO.getCardNumber());

        validateTransaction(buyCardBalanceDTO, card);

        BigDecimal computedCashBackValue = card.getTypeCard().computeBuyValue(buyCardBalanceDTO.getValue());
        card.setCardBalance(card.getCardBalance().subtract(computedCashBackValue));
        cardRepository.save(card);


        Extract extract = Extract.builder()
                .establishmentId(buyCardBalanceDTO.getEstablishmentId())
                .establishmentName(buyCardBalanceDTO.getEstablishmentName())
                .productDescription(buyCardBalanceDTO.getProductDescription())
                .dateBuy(new Date(System.currentTimeMillis()))
                .cardNumber(card.getCardNumber())
                .value(buyCardBalanceDTO.getValue())
                .build();
        extractRepository.save(extract);
    }

    private void validateTransaction(BuyCardBalanceDTO buyCardBalanceDTO, Card card) {
        boolean validEstablishmentType = toEnum(buyCardBalanceDTO.getEstablishmentType())
                .getAcceptedCardTypes()
                .contains(card.getTypeCard());

        if (!validEstablishmentType) {
            throw new InvalidTransactionException(
                    String.format("Estabelecimento tipo { %s } não aderente ao cartão de tipo { %s }.",
                            toEnum(buyCardBalanceDTO.getEstablishmentType()),
                            card.getTypeCard()));
        }

        boolean sufficientBalance = card.getCardBalance().compareTo(buyCardBalanceDTO.getValue()) >= 0;
        if (!sufficientBalance) {
            throw new InvalidTransactionException(
                    String.format("Saldo do cartão { %s } insuficiente para operação no valor de { %s }.",
                            card.getCardBalance(),
                            buyCardBalanceDTO.getValue()));
        }

    }
}
