package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.integration.respository.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public Page<Consumer> findAll(Pageable pageable) {
        return consumerRepository.findAll(pageable);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Consumer save(Consumer consumer) throws ApiException {
        try {
            return consumerRepository.save(consumer);
        } catch (Exception e) {
            log.error("m=save, stage=error, excption={}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_EXCEPTION);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public Consumer update(Consumer consumer) throws ApiException {
        try {
            Optional<Consumer> consumerOut = consumerRepository.findByConsumerCode(consumer.getConsumerCode());
            if (consumerOut.isEmpty()) {
                throw new ApiException(HttpStatus.NOT_FOUND, Code.INVALID_EXCEPTION);
            }
            if (!checkCardBalance(consumer.getCards(), consumerOut.get().getCards())) {
                throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_UPDATE);
            }
            consumer.setId(consumerOut.get().getId());
            return consumerRepository.save(consumer);
        } catch (ApiException e) {
            log.warn("m=update, stage=warn, excption={}", e.getCode().getMessage());
            throw e;
        } catch (Exception e) {
            log.error("m=update, stage=error, excption={}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_EXCEPTION);
        }
    }

    public boolean checkCardBalance(Set<Card> cardToCompare, Set<Card> cardCompared) {
        Set<BigDecimal> cardOne = cardToCompare.stream().map(Card::getBalance).collect(Collectors.toSet());
        Set<BigDecimal> cardTwo = cardCompared.stream().map(Card::getBalance).collect(Collectors.toSet());
        int initial = cardOne.size();
        cardTwo.removeIf(cardOne::contains);
        return (initial - cardTwo.size()) == 0;
    }

}
