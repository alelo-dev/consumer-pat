package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import br.com.alelo.consumer.consumerpat.data.entity.Extract;
import br.com.alelo.consumer.consumerpat.data.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.data.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.domain.Purchase;
import br.com.alelo.consumer.consumerpat.domain.exception.IndustryMismatchException;
import br.com.alelo.consumer.consumerpat.domain.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.domain.exception.UnknownCardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class CardService {

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    CardRepository cardRepository;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indentificar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    public void addBalance(String cardNumber, BigDecimal value) throws UnknownCardException {
        Card card = findByCardNumber(cardNumber);
        card.setBalance(card.getBalance().add(value));
        cardRepository.save(card);
    }

    public Page<Extract> findCardExtract(String cardNumber, int size, int page) {
        return extractRepository.findAllByCardNumber(cardNumber, PageRequest.of(page, size, Sort.by("dateBuy").descending()));
    }

    public Card findByCardNumber(String cardNumber) throws UnknownCardException {
        return cardRepository.findById(cardNumber).orElseThrow(UnknownCardException::new);
    }

    public List<Card> findByConsumerId(Integer consumerId) {
        return cardRepository.findByConsumerId(consumerId);
    }

    public Card buy(Purchase purchase) throws UnknownCardException, IndustryMismatchException, InsufficientFundsException {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        Card card = findByCardNumber(purchase.getCardNumber());
        if (!card.getType().equals(purchase.getEstablishmentType())) throw new IndustryMismatchException();
        BigDecimal finalValue = calcPurchaseValue(purchase);
        BigDecimal newBalance = card.getBalance().subtract(finalValue);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) throw new InsufficientFundsException();
        card.setBalance(newBalance);
        cardRepository.save(card);
        saveExtract(purchase, finalValue);
        return card;
    }

    private BigDecimal calcPurchaseValue(Purchase purchase) {
        BigDecimal value = purchase.getValue();
        BigDecimal cashback = BigDecimal.ZERO;
        BigDecimal tax = BigDecimal.ZERO;
        switch (purchase.getEstablishmentType()) {
            case FOOD:
                cashback = value.multiply(new BigDecimal("0.1")); // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
                break;
            case FUEL:
                tax = value.multiply(new BigDecimal("0.35")); // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
                break;
        }
        return value.subtract(cashback).add(tax);
    }

    private void saveExtract(Purchase purchase, BigDecimal finalValue) {
        Extract extract = new Extract(
                purchase.getEstablishmentName(),
                purchase.getProductDescription(),
                new Date(),
                purchase.getCardNumber(),
                finalValue
        );
        extractRepository.save(extract);
    }
}
