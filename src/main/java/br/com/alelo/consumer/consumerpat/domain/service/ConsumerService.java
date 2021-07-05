package br.com.alelo.consumer.consumerpat.domain.service;

import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.service.exception.Code;
import br.com.alelo.consumer.consumerpat.integration.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.integration.respository.ConsumerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private CardRepository cardRepository;

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
                throw new ApiException(HttpStatus.NOT_FOUND, Code.INVALID_NOT_FOUND);
            }
            consumer.setCards(mergeCards(consumerOut.get().getCards(), consumer.getCards()));
            consumer.setId(consumerOut.get().getId());
            consumer.getCards().forEach(card -> cardRepository.save(card));

            return consumerRepository.save(consumer);
        } catch (ApiException e) {
            log.warn("m=update, stage=warn, excption={}", e.getCode().getMessage());
            throw e;
        }catch (ConcurrentModificationException e){
            throw new ApiException(HttpStatus.NOT_FOUND, Code.INVALID_CARD_NOT_FOUND);
        } catch (Exception e) {
            log.error("m=update, stage=error, excption={}", e.getMessage());
            throw new ApiException(HttpStatus.BAD_REQUEST, Code.INVALID_EXCEPTION);
        }
    }

    public Set<Card> mergeCards(Set<Card> cardToCompare, Set<Card> cardCompared) {
        Map<String, Card> mapCardToCompare = cardToCompare.stream().collect(Collectors.toMap(Card::getCardCode, e -> e));
        Map<String, Card> mapCardCompared = cardCompared.stream().collect(Collectors.toMap(Card::getCardCode, e -> e));
        Map<String, Card> mapMerged = new HashMap<>(mapCardToCompare);

        mapCardCompared.forEach(
                (key, value) -> mapMerged.merge(key, value, (v1, v2) -> Card.builder().id(v1.getId()).balance(v1.getBalance()).type(v2.getType()).cardCode(v2.getCardCode()).number(v2.getNumber()).build()));

       mapMerged.forEach((key, value) -> {
           if(value.getBalance() == null){
               mapMerged.remove(key);
           }
       });

        return new HashSet<>(mapMerged.values());
    }

}
