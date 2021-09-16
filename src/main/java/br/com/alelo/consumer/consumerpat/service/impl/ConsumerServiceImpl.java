package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerBuy;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerSetCardBalance;
import br.com.alelo.consumer.consumerpat.helper.RequestConsumerUpdate;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired 
    CardRepository cardRepository;

    @Override
    public Page<Consumer> findConsumers(PageRequest page) {
        return consumerRepository.findAll(page);
    }

    @Override
    public void buy(RequestConsumerBuy requestConsumerBuy) {
        Optional<Card> cardOptional = cardRepository.findByCardNumber(requestConsumerBuy.getCardNumber());
        if (cardOptional.isEmpty()) {
            throw new BusinessException("Cartãozinho não encontrado");
        }
        Card card = cardOptional.get();
        BigDecimal cashback =  requestConsumerBuy.getValue().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(card.getTypeCard().getDiscountPercent()));
        BigDecimal tax = requestConsumerBuy.getValue().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(card.getTypeCard().getAdditionPercent()));
        requestConsumerBuy.setValue(requestConsumerBuy.getValue().add(tax).subtract(cashback));
        card.setBalance(card.getBalance().subtract(requestConsumerBuy.getValue()));
        cardRepository.save(card);
        Extract extract = Extract.builder().card(card).product(requestConsumerBuy.getProduct())
        .establishment(requestConsumerBuy.getEstablishment()).dateBuy(LocalDateTime.now()).
        value(requestConsumerBuy.getValue()).build();
        extractRepository.save(extract);
    }

    @Override
    public void setBalance(RequestConsumerSetCardBalance requestConsumerSetCardBalance) {
        Optional<Card> cardOptional = cardRepository.findByCardNumber(requestConsumerSetCardBalance.getCardNumber());
        if (cardOptional.isEmpty()) {
            throw new BusinessException("Cartãozinho não encontrado");
        }
        Card card = cardOptional.get();
        cardRepository.save(card);
        card.setBalance(card.getBalance().add(requestConsumerSetCardBalance.getValue()));
    }

    @Override
    public void createConsumer(Consumer consumer) {
        consumerRepository.save(consumer);
    }

    @Override
    public void updateConsumer(Long id, RequestConsumerUpdate requestConsumerUpdate) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        if (consumerOptional.isEmpty()) {
            throw new BusinessException("Cliente não encontrado");
        }
        Consumer consumer = consumerOptional.get();
        Consumer consumerRequest = new Consumer(requestConsumerUpdate);
        consumerRequest.setCards(consumer.getCards());
        consumerRepository.save(consumer);
    }

    
}