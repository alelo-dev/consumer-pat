package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.Brands;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ExtractRepository extractRepository;

    //listat todos os Clientes
    public Page<Consumer> listAllConsumers (Pageable pageable) {
        return repository.findAll(pageable);
    }

    //salver clientes
    public Consumer saveConsumers(Consumer consumer) {
        return repository.save(consumer);
    }

    public Consumer updateConsumers(Consumer consumer) {
        consumer.setId(consumer.getId());
        consumer = repository.save(consumer);

        return consumer;
    }

    public Consumer setBalance(Long cardNumber, Long value, Integer brands) {
        Brands brandsEnum = Brands.getEnumById(brands);
        Consumer consumer =  brandsEnum.setBalance(repository, cardNumber, value);
        return consumer;
    }

    public Consumer buy(Integer establishmentType,
                    String establishmentName,
                    Long cardNumber,
                    String productDescription,
                    Long value) {

        Brands brandsEnum = Brands.getEnumById(establishmentType);
        Consumer consumer = brandsEnum.setBuy(repository, cardNumber, value);

        if (Objects.nonNull(consumer)) {
            Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
            extractRepository.save(extract);
            return consumer;
        }

        return consumer;
    }

}
