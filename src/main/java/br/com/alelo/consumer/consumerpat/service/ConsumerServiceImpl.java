package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerRepository consumerRepository;
    private CardService cardService;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository, CardService cardService) {
        this.consumerRepository = consumerRepository;
        this.cardService = cardService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConsumerDTO> listAllConsumers(Pageable pageable) {
        Page<Consumer> consumers = consumerRepository.findAll(pageable);
        return consumers.map(consumer -> new ConsumerDTO(consumer));
    }

    @Override
    @Transactional
    public ResultStatus createConsumer(ConsumerDTO consumerDTO) {
        save(consumerDTO);
        return ResultStatus.builder().isOk(true).build();
    }

    @Override
    @Transactional
    public ResultStatus updateConsumer(ConsumerDTO consumerDTO) {
        save(consumerDTO);
        return ResultStatus.builder().isOk(true).build();
    }

    @Override
    @Transactional
    public ResultStatus addCard(Long idConsumer, CardDTO cardDTO) {
        Optional<Consumer> consumer = consumerRepository.findById(idConsumer);
        if (consumer.isEmpty()) {
            return ResultStatus.builder().isOk(false).error("Consumer nao encontrado").build();
        }
        cardService.addCard(idConsumer, cardDTO);
        return ResultStatus.builder().isOk(true).build();
    }

    private void save(ConsumerDTO consumerDTO) {
        Consumer consumer = toConsumer(consumerDTO);
        consumer.setCards(null);
        consumerRepository.save(consumer);
    }

    private Consumer toConsumer(ConsumerDTO consumerDTO) {
        Consumer entity = new Consumer();
        BeanUtils.copyProperties(consumerDTO, entity);
        return entity;
    }

}
