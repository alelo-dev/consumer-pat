package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public Page<ConsumerResponse> listAllConsumers(Pageable pageable) {
        log.info("finding page with request = {}", pageable);

        Page<Consumer> consumers = consumerRepository.findAll(pageable);

        return consumers.map(consumerMapper::toResponse);
    }

    @Override
    public ConsumerResponse createConsumer(ConsumerRequest consumerRequest) {
        log.info("creating consumer with data = {}", consumerRequest);

        Consumer entity = consumerMapper.toEntity(consumerRequest);

        validateDocument(consumerRequest);

        Consumer savedConsumer = consumerRepository.save(entity);

        return consumerMapper.toResponse(savedConsumer);
    }

    @Override
    public ConsumerResponse updateConsumer(Long id, ConsumerRequest consumerRequest) {
        log.info("Updating consumer with id = {}; data = {}", id, consumerRequest);

        Consumer consumer =  consumerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Consumer not found", HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(consumerRequest, consumer);

        Consumer savedConsumer = consumerRepository.save(consumer);

        return consumerMapper.toResponse(savedConsumer);
    }

    @Override
    public ConsumerResponse findCardsForConsumer(String documentNumber) {
        log.info("finding consumer for document = {}", documentNumber);

        Consumer consumer = consumerRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new BusinessException("Consumer not found", HttpStatus.NOT_FOUND));

        return consumerMapper.toResponse(consumer);
    }

    private void validateDocument(ConsumerRequest consumerRequest) {

        if (consumerRepository.existsDocument(consumerRequest.getDocumentNumber())) {
            throw new BusinessException("Document already exists in base", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



}
