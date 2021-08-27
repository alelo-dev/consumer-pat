package br.com.alelo.consumer.consumerpat.dao;

import br.com.alelo.consumer.consumerpat.DTO.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class ConsumerImplementDAO implements ConsumerDAO {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @Transactional
    public List<Consumer> listAllConsumersDao() {
        return repository.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    @Transactional
    public void createConsumeDao(Consumer consumer) {
        repository.save(consumer);
    }

    @Override
    public void updateConsumerDao(Consumer consumer) {

    }

    // Não deve ser possível alterar o saldo do cartão
    @Transactional
    public void updateConsumerDao(ConsumerDTO consumerDTO) {

        Optional<Consumer> consumer = repository.findById(consumerDTO.getId());

        if(consumer.isPresent()) {

            consumer.get().setName(consumerDTO.getName());
            consumer.get().setDocumentNumber(consumerDTO.getDocumentNumber());
            consumer.get().setBirthDate(consumerDTO.getBirthDate());
            consumer.get().setMobilePhoneNumber(consumerDTO.getMobilePhoneNumber());
            consumer.get().setResidencePhoneNumber(consumerDTO.getResidencePhoneNumber());
            consumer.get().setPhoneNumber(consumerDTO.getPhoneNumber());
            consumer.get().setEmail(consumerDTO.getEmail());
            consumer.get().setStreet(consumerDTO.getStreet());
            consumer.get().setNumber(consumerDTO.getNumber());
            consumer.get().setCity(consumerDTO.getCity());
            consumer.get().setCountry(consumerDTO.getCountry());
            consumer.get().setPortalCode(consumerDTO.getPortalCode());
            consumer.get().setBirthDate(consumerDTO.getBirthDate());
            consumer.get().setFoodCardNumber(consumerDTO.getFoodCardNumber());
            consumer.get().setFuelCardNumber(consumerDTO.getFuelCardNumber());
            consumer.get().setDrugstoreNumber(consumerDTO.getDrugstoreNumber());

            repository.save(consumer.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado!");
        }
    }

}