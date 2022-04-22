package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.City;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Country;
import br.com.alelo.consumer.consumerpat.entity.State;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.services.CityService;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import br.com.alelo.consumer.consumerpat.services.CountryService;
import br.com.alelo.consumer.consumerpat.services.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final CityService cityService;
    private final StateService stateService;
    private final CountryService countryService;


    public ConsumerServiceImpl(ConsumerRepository consumerRepository, CityService cityService,
                               StateService stateService, CountryService countryService) {
        this.consumerRepository = consumerRepository;
        this.cityService = cityService;
        this.stateService = stateService;
        this.countryService = countryService;
    }

    @Override
    public Page<Consumer> findAll(Specification<Consumer> filter, Pageable pageable) {
        return consumerRepository.findAll(filter, pageable);
    }

    @Transactional
    @Override
    public Consumer save(Consumer consumer) {

        Country currentCountry = countryService.findOrFail(consumer.getAddress().getCity().getState().getCountry().getId());
        consumer.getAddress().getCity().getState().setCountry(currentCountry);

        State currentState = stateService.findOrFail(consumer.getAddress().getCity().getState().getId());
        consumer.getAddress().getCity().setState(currentState);

        City currentCity = cityService.findOrFail(consumer.getAddress().getCity().getId());
        consumer.getAddress().setCity(currentCity);

        consumer.getContacts().forEach(c -> c.setConsumer(consumer));

        return consumerRepository.save(consumer);
    }

    @Transactional
    @Override
    public Consumer update(Long id, Consumer consumer) {

        Consumer savedConsumer = findOrFail(id);

        //Por algum motivo utilizando o BD H2 a exclusão dos contatos do BD antes da atualização não funciona.
        //Porém com o PostgreSQL funciona normalmente.Sendo assim duplica os contatos durante a atualização.
        //Fiz alguns testes com repositório para contatos / flush e anotações mas o tempo não foi suficiente para encontrar uma solução.
        savedConsumer.getContacts().clear();
        savedConsumer.getContacts().addAll(consumer.getContacts());
        savedConsumer.getContacts().forEach(c -> c.setConsumer(savedConsumer));

        BeanUtils.copyProperties(consumer, savedConsumer, "id", "cards", "address", "contacts");

        return consumerRepository.save(savedConsumer);

    }

    @Override
    public Consumer findOrFail(Long id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new ConsumerNotFoundException(id) {
                });
    }


}
