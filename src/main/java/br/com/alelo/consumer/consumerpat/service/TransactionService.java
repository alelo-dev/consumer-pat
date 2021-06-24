package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.StatementAccount;
import br.com.alelo.consumer.consumerpat.repository.StatementAccountRepository;
import br.com.alelo.consumer.consumerpat.parameter.TransactionParameter;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class TransactionService {

    private StatementAccountRepository statementAccountRepository;
    private CardRepository cardRepository;
    private ConsumerService consumerService;

    @Transactional
    public void buy(TransactionParameter parameter) {
        validateConsumer(parameter);
        updateCard(parameter);
        createStatementAccount(parameter);
    }

    private void validateConsumer(TransactionParameter parameter) {
        if (!consumerService.verifyExistsConsumerById(parameter.getConsumerId())) {
            throw new EntityNotFoundException("Cliente não localizado");
        }
    }

    private void updateCard(TransactionParameter parameter) {
        if (parameter.getTransactionAmount().doubleValue() < 0) {
            throw new IllegalArgumentException("Valor da transação deve ser maior que zero");
        }
        Card card = getAndValidatedCard(parameter);
        parameter.setTransactionAmount(getAndValidatedTransactionAmountWithAdjustment(parameter, card));
        card.registerTransactionDebit(parameter.getTransactionAmount());
        cardRepository.save(card);
    }

    private void createStatementAccount(TransactionParameter parameter) {
        StatementAccount statementAccount = StatementAccount.fromTransactionParameter(parameter);
        statementAccountRepository.save(statementAccount);
    }

    private Card getAndValidatedCard(TransactionParameter parameter)
            throws EntityNotFoundException, IllegalArgumentException {
        Card card = consumerService.getCard(parameter.getCardNumber(), parameter.getConsumerId());
        if (parameter.getCardType() != card.getType()) {
            throw new IllegalArgumentException("Tipo de Cartão inválido");
        }
        return card;
    }

    public BigDecimal getAndValidatedTransactionAmountWithAdjustment(TransactionParameter parameter, Card card) throws IllegalArgumentException {

        BigDecimal transactionAmountWithAdjustment = parameter.getTransactionAmount().multiply(parameter.getCardType().getAdjustment());
        if (transactionAmountWithAdjustment.doubleValue() > card.getBalance().doubleValue()) {
            throw new IllegalArgumentException("Saldo do cartão menor que o valor da transação");
        }
        return transactionAmountWithAdjustment;
    }
}