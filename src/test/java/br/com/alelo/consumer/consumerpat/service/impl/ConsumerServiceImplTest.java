package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.ConsumerCreate;
import br.com.alelo.consumer.consumerpat.model.ConsumerUpdate;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceImplTest {
    @Mock
    private ConsumerRepository repository;

    @Mock
    private ExtractRepository extractRepository;

    @Mock
    private ExtractService extractService = new ExtractServiceImpl(extractRepository);

    @InjectMocks
    private ConsumerService consumerService = new ConsumerServiceImpl(repository, extractService);

    private final List<Consumer> consumersMocked = List.of(
            mockConsumer(1, 2, 3, 4),
            mockConsumer(2, 5, 6, 7));

    Consumer mockConsumer(int id, int foodCardNumber, int fuelCardNumber, int drugstoreCardNumber) {
        var consumer = new Consumer();
        consumer.setConsumerId(id);
        consumer.setFoodCardNumber(foodCardNumber);
        consumer.setFuelCardNumber(fuelCardNumber);
        consumer.setDrugstoreCardNumber(drugstoreCardNumber);
        return consumer;
    }

    @Test
    void testCreation() {
        var consumer = new ConsumerCreate();

        consumer.setFuelCardNumber(1);
        consumer.setDrugstoreCardNumber(2);
        consumer.setFoodCardNumber(3);

        Mockito.when(repository.save(consumer.toEntity())).thenReturn(consumer.toEntity());

        consumerService.create(consumer);

        Mockito.verify(repository, Mockito.times(1)).save(consumer.toEntity());
    }

    @Test
    void testUpdate() {
        var consumer = new ConsumerUpdate();
        consumer.setConsumerId(consumersMocked.get(0).getConsumerId());

        var consumerEntity = consumer.toEntity();

        consumerEntity.setConsumerId(consumersMocked.get(0).getConsumerId());
        consumerEntity.setFoodCardBalance(consumersMocked.get(0).getFoodCardBalance());
        consumerEntity.setFuelCardBalance(consumersMocked.get(0).getFuelCardBalance());
        consumerEntity.setDrugstoreCardBalance(consumersMocked.get(0).getDrugstoreCardBalance());

        Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(consumersMocked.get(0)));

        Mockito.when(repository.save(consumerEntity)).thenReturn(consumerEntity);

        consumerService.update(consumer);

        Mockito.verify(repository, Mockito.times(1)).save(consumerEntity);
    }

    @Test
    void testUpdate_ThrowExceptionConsumerNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            var consumer = new ConsumerUpdate();
            consumer.setConsumerId(1);

            Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

            consumerService.update(consumer);
        });
    }

    @Test
    void testGetAll() {
        Mockito.when(repository.findAll()).thenReturn(consumersMocked);
        var consumers = consumerService.getAll();

        Mockito.verify(repository, Mockito.times(1)).findAll();
        assertEquals(consumers, consumersMocked);
    }
}
