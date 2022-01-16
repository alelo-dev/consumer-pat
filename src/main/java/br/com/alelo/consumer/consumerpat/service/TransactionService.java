package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.model.Transaction;
import br.com.alelo.consumer.consumerpat.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(String establishmentName, BigInteger cardNumber, String productDescription, BigDecimal value) {
        Transaction transaction = new Transaction();
        transaction.setEstablishmentName(establishmentName);
        transaction.setProductDescription(productDescription);
        transaction.setPurchaseDateTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        transaction.setCardNumber(cardNumber);
        transaction.setValue(value);
        transactionRepository.save(transaction);
        return transaction;
    }
}
