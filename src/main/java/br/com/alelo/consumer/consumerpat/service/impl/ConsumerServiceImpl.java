package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ConsumerMapper mapper;

    public Page<ConsumerResponse> findAllConsumers(Pageable pageable) {
        log.info("Finding page request = {}", pageable);
        Page<Consumer> consumers = repository.findAll(pageable);
        return consumers.map(mapper::toResponse);
    }

    public ConsumerResponse save(ConsumerRequest consumerRequest) {
        log.info("Creating consumer = {}", consumerRequest);
        Consumer consumer = mapper.toRequest(consumerRequest);
        return mapper.toResponse(repository.save(consumer));
    }

    public ConsumerResponse update(Integer id, ConsumerRequest consumerRequest) {
        log.info("Updating consumer id = {}; info = {}", id, consumerRequest);
        Consumer consumer = repository.findById(id).orElseThrow(() -> new RuntimeException("Consumer Not Found"));
        BeanUtils.copyProperties(consumerRequest, consumer);
        return mapper.toResponse(repository.save(consumer));
    }
}
