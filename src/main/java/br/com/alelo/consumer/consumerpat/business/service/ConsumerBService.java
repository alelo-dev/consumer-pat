package br.com.alelo.consumer.consumerpat.business.service;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 08:15
 */

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerCreationException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverCustomersException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerRecoverNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.Applog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ConsumerBService {

    private final ConsumerRepository consumerRepository;

    public ConsumerBService(ConsumerRepository consumerRepository){
        this.consumerRepository = consumerRepository;
    }

    public Page<Consumer> recoverAllCustomers(Pageable pageable) {
        Applog.infoStart(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            return this.consumerRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ConsumerRecoverCustomersException();
        } finally {
            Applog.infoEnd(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public Consumer save(Consumer consumer) {
        Applog.infoStart(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            return this.consumerRepository.save(consumer);
        } catch (Exception e) {
            throw new ConsumerCreationException(Objects.isNull(consumer.getId()));
        } finally {
            Applog.infoEnd(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public Consumer recoverById(Integer value) {
        Applog.infoStart(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            Optional<Consumer> optionalConsumer = this.consumerRepository.findById(value);
            return optionalConsumer.orElseThrow(ConsumerRecoverNotFoundException::new);
        } catch (Exception e) {
            throw new ConsumerRecoverCustomersException();
        } finally {
            Applog.infoEnd(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }

    public Consumer recoverByDocumentNumber(String value) {
        Applog.infoStart(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        try {
            return this.consumerRepository.findByDocumentNumber(value).orElse(null);
        } catch (Exception e) {
            throw new ConsumerRecoverCustomersException();
        } finally {
            Applog.infoEnd(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName());
        }
    }
}
