package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    public List<Consumer> getAllConsumersList() {

        return repository.findAll();
    }

    public Consumer findConsumerById(Integer id) {

        Optional<Consumer> cons = repository.findById(id);

        return cons.orElseThrow(() -> new NullPointerException(
                "Consumidor não encontrado! Id: " + id + ", Tipo: " + Consumer.class.getName()));
    }

    public Consumer findByFoodCardNumber(Long cardNumber) {

        Optional<Consumer> cons = Optional.ofNullable(repository.findByFoodCardNumber(cardNumber));

        return cons.orElse(null);
    }

    public Consumer findByFuelCardNumber(Long cardNumber) {

        Optional<Consumer> cons = Optional.ofNullable(repository.findByFuelCardNumber(cardNumber));

        return cons.orElse(null);
    }

    public Consumer findByDrugstoreNumber(Long cardNumber) {

        Optional<Consumer> cons = Optional.ofNullable(repository.findByDrugstoreNumber(cardNumber));

        return cons.orElse(null);
    }

    public Consumer createConsumer (Consumer cons) {

        cons.setId(null);

        return repository.save(cons);
    }

    public Consumer update(Consumer consumer) {
        Consumer newConsumer = findConsumerById(consumer.getId());
        updateData(newConsumer, consumer);
        return repository.save(newConsumer);
    }

    public Consumer fromDTO(ConsumerDTO consumerDTO) {

        return new Consumer(consumerDTO.getId(), consumerDTO.getName(), consumerDTO.getDocumentNumber(),
                consumerDTO.getBirthDate(), consumerDTO.getMobilePhoneNumber(), consumerDTO.getPhoneNumber(),
                consumerDTO.getResidencePhoneNumber(), consumerDTO.getEmail(), consumerDTO.getStreet(),
                consumerDTO.getNumber(), consumerDTO.getCity(), consumerDTO.getCountry(), consumerDTO.getPortalCode(),
                consumerDTO.getFoodCardNumber(), consumerDTO.getFuelCardNumber(), consumerDTO.getDrugstoreNumber());
    }

    public void save(Consumer consumer) {
        repository.save(consumer);
    }

    private void updateData(Consumer newConsumer, Consumer consumer) {

        newConsumer.setName((consumer.getName() != null) ?
                consumer.getName() : newConsumer.getName());
        newConsumer.setDocumentNumber((consumer.getDocumentNumber() != 0) ?
                consumer.getDocumentNumber() : newConsumer.getDocumentNumber());
        newConsumer.setBirthDate((consumer.getBirthDate() != null) ?
                consumer.getBirthDate() : newConsumer.getBirthDate());

        newConsumer.setMobilePhoneNumber((consumer.getMobilePhoneNumber() != 0) ?
                consumer.getMobilePhoneNumber() : newConsumer.getMobilePhoneNumber());
        newConsumer.setResidencePhoneNumber((consumer.getResidencePhoneNumber() != 0) ?
                consumer.getResidencePhoneNumber() : newConsumer.getResidencePhoneNumber());
        newConsumer.setPhoneNumber((consumer.getPhoneNumber() != 0) ?
                consumer.getPhoneNumber() : newConsumer.getPhoneNumber());
        newConsumer.setEmail((consumer.getEmail() != null) ?
                consumer.getEmail() : newConsumer.getEmail());

        newConsumer.setStreet((consumer.getStreet() != null) ?
                consumer.getStreet() : newConsumer.getStreet());
        newConsumer.setNumber((consumer.getNumber() != 0) ?
                consumer.getNumber() : newConsumer.getNumber());
        newConsumer.setCity((consumer.getCity() != null) ?
                consumer.getCity() : newConsumer.getCity());
        newConsumer.setCountry((consumer.getCountry() != null) ?
                consumer.getCountry() : newConsumer.getCountry());
        newConsumer.setPortalCode((consumer.getPortalCode() != 0) ?
                consumer.getPortalCode() : newConsumer.getPortalCode());
        newConsumer.setFoodCardNumber((consumer.getFoodCardNumber() != null) ?
                consumer.getFoodCardNumber() : newConsumer.getFoodCardNumber());
        newConsumer.setFuelCardNumber((consumer.getFuelCardNumber() != null) ?
                consumer.getFuelCardNumber() : newConsumer.getFuelCardNumber());
        newConsumer.setDrugstoreNumber((consumer.getDrugstoreNumber() != null) ?
                consumer.getDrugstoreNumber() : newConsumer.getDrugstoreNumber());
    }

    public Consumer findConsumerByCard(Long cardNumber) {
        Optional<Consumer> cons =
                Optional.ofNullable(findByFoodCardNumber(cardNumber));

        if (Objects.isNull(cons)) {
            cons = Optional.ofNullable(findByDrugstoreNumber(cardNumber));
        }else if (Objects.isNull(cons)) {
            cons = Optional.ofNullable(findByFuelCardNumber(cardNumber));
        }

        return cons.orElseThrow(() -> new NullPointerException(
                "Cartão não encontrado! Cartão: " + cardNumber + ", Tipo: " + Consumer.class.getName()));
    }

    public Page<Consumer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return repository.findAll(pageRequest);
    }

    public Consumer addCreditValue(Long cardNumber, double value) {

        Consumer consumer = findByDrugstoreNumber(cardNumber);
        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
        } else {
            consumer = findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
            } else {
                // É cartão de combustivel
                consumer = findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
            }
        }
        save(consumer);
        return consumer;
    }
}
