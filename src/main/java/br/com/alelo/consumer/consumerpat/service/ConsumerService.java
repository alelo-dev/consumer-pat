package br.com.alelo.consumer.consumerpat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.GetConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

    private static final String CONSUMER_DONT_FOUND = "Consumidor não encontrado";

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ConsumerMapper consumerMapper;

    public void createConsumer(CreateConsumerDTO dto) {
        var consumer = consumerMapper.createToEntity(dto);
        repository.save(consumer);
    }

    public void updateConsumer(UpdateConsumerDTO dto, int id) {
        var consumer = consumerMapper.updateToEntity(dto);
        var actualConsumer = repository.findById(id).orElseThrow(() -> new ConsumerNotFoundException(CONSUMER_DONT_FOUND));
        BeanUtils.copyProperties(consumer, actualConsumer,
                    "id", "foodCardNumber", "foodCardBalance", "fuelCardNumber", "fuelCardBalance", "drugstoreNumber", "drugstoreCardBalance");
        repository.save(actualConsumer);
    }

    public void balance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }

    public List<GetConsumerDTO> getAllConsumers(){
        return consumerMapper.toListDTO(repository.getAllConsumersList());
    }
    
}
