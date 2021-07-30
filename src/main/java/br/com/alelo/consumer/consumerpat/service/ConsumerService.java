package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerIdDTO;
import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;
import br.com.alelo.consumer.consumerpat.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.alelo.consumer.consumerpat.util.ConsumerValidation.shouldHaveId;
import static br.com.alelo.consumer.consumerpat.util.ConsumerValidation.validate;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ConsumerRepository consumerRepository;
    private final ConsumerMapper consumerMapper;

    public List<ConsumerDTO> getAllConsumersList() {
        return consumerRepository.findAllWithCards()
                .stream()
                .map(consumerMapper::getConsumerDTOFrom)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumerIdDTO create(CreateConsumerDTO dto) {
        Consumer consumer = consumerMapper.getConsumerFrom(dto);
        setDefaults(consumer);
        validate(consumer);
        return consumerMapper.getConsumerIdDTOFrom(consumerRepository.save(consumer));
    }

    @Transactional
    public ConsumerIdDTO update(UpdateConsumerDTO dto) {
        shouldHaveId(dto.getId());
        Consumer consumerDb = findById(dto.getId());
        ObjectUtils.copyNonNullProperties(dto, consumerDb);
        Consumer consumer = consumerMapper.getConsumerFrom(dto);
        validate(consumer);
        updateCards(consumer, consumerDb);

        return consumerMapper.getConsumerIdDTOFrom(consumerRepository.save(consumerDb));
    }

    private void updateCards(Consumer consumer, Consumer consumerDb) {
        if (nonNull(consumer.getCards())) {
            final List<Card> cardsToAdd = new LinkedList<>();
            final List<Card> cardList = Optional.ofNullable(consumerDb.getCards()).orElse(Collections.emptyList());
            Map<Long, Card> cardsMap = cardList
                    .stream()
                    .collect(Collectors.toMap(Card::getId, Function.identity()));
            consumer.getCards().forEach(it -> {
                if (cardsMap.containsKey(it.getId())) {
                    final Card cardDB = cardsMap.get(it.getId());
                    cardDB.setNumber(it.getNumber());
                    cardDB.setTypes(it.getTypes());
                } else {
                    it.setId(null);
                    setDefault(it, consumerDb);
                    cardsToAdd.add(it);
                }
            });
            cardList.addAll(cardsToAdd);
            consumer.setCards(cardList);
        }
    }

    public Consumer findById(Long id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CONSUMER_ID_NOT_FOUND, id),
                        Constants.CONSUMER_NOT_FOUND));
    }

    private void setDefaults(Consumer entity) {
        entity.setCards(Optional.ofNullable(entity.getCards())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(it -> {
                    setDefault(it, entity);
                    return it;
                })
                .collect(Collectors.toList()));
    }

    private void setDefault(Card it, Consumer entity) {
        it.setConsumer(entity);
        it.setBalance(Optional.ofNullable(it.getBalance())
                .orElse(BigDecimal.ZERO));
    }
}
