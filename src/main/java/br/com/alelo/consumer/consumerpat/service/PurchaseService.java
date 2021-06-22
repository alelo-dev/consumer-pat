package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.Messages;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Statement;
import br.com.alelo.consumer.consumerpat.parameter.PurchaseParameter;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.StatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class PurchaseService {

    private StatementRepository statementRepository;
    private CardRepository cardRepository;
    private ConsumerService consumerService;
    private Messages messages;

    @Transactional
    public void buy(PurchaseParameter parameter) {
        validateConsumer(parameter);
        updateCard(parameter);
        createStatement(parameter);
    }

    private void validateConsumer(PurchaseParameter parameter) {
        if (!consumerService.existsConsumerById(parameter.getConsumerId())) {
            throw new EntityNotFoundException(messages.consumerNotFound);
        }
    }

    private void updateCard(PurchaseParameter parameter) {
        Card card = getValidatedCard(parameter);
        parameter.setProductValue(getValidatedFinalProductValue(parameter, card));
        card.registerPurchase(parameter.getProductValue());
        cardRepository.save(card);
    }

    private void createStatement(PurchaseParameter parameter) {
        Statement statement = Statement.fromPurchaseParameter(parameter);
        statementRepository.save(statement);
    }

    private Card getValidatedCard(PurchaseParameter parameter) throws EntityNotFoundException, IllegalArgumentException {
        Card card = consumerService.getCardOrException(parameter.getConsumerId(), parameter.getCardNumber());

        if (card.getType() != parameter.getType()) {
            throw new IllegalArgumentException(messages.invalidCardType);
        }

        return card;
    }

    public BigDecimal getValidatedFinalProductValue(PurchaseParameter parameter, Card card)
            throws IllegalArgumentException {
        BigDecimal finalProductValue =
                parameter.getProductValue().multiply(parameter.getType().getPercentageAdjustement());

        if (finalProductValue.doubleValue() > card.getBalance().doubleValue()) {
            throw new IllegalArgumentException(messages.insufficientBalance);
        }

        return finalProductValue;
    }

}
