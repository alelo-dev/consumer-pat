package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ConsumerServiceImplTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    ConsumerServiceImpl consumerService;

    @Test
    void listAllConsumers() {

        List<Consumer> consumerList = new ArrayList<>();
        consumerList.add(new Consumer());

        when(consumerRepository.findAll()).thenReturn(consumerList);

        List consumerListReturned = consumerService.listAllConsumers();

        Assertions.assertTrue(!consumerListReturned.isEmpty());
    }

    @Test
    void setBalanceFuel() {

        Consumer consumer = getConsumer();
        when(consumerRepository.findByCardNumber("999")).thenReturn(Optional.of(consumer));

        consumerService.setBalance("999", BigDecimal.valueOf(100));

        Consumer updatedConsumer = getConsumer();
        updatedConsumer.setFuelCardBalance(BigDecimal.valueOf(110));

        verify(consumerRepository, times(1)).save(updatedConsumer);
    }

    @Test
    void setBalanceFood() {

        Consumer consumer = getConsumer();
        when(consumerRepository.findByCardNumber("888")).thenReturn(Optional.of(consumer));

        consumerService.setBalance("888", BigDecimal.valueOf(100));

        Consumer updatedConsumer = getConsumer();
        updatedConsumer.setFoodCardBalance(BigDecimal.valueOf(110));

        verify(consumerRepository, times(1)).save(updatedConsumer);
    }

    private Consumer getConsumer() {

        String fuelCardNumber = "999";
        String foodCardNumber = "888";
        String drugStoreCardNumber = "777";
        Consumer consumer = new Consumer();
        consumer.setFuelCardNumber(fuelCardNumber);
        consumer.setDrugStoreNumber(drugStoreCardNumber);
        consumer.setFoodCardNumber(foodCardNumber);
        consumer.setFuelCardBalance(BigDecimal.TEN);
        consumer.setDrugStoreCardBalance(BigDecimal.TEN);
        consumer.setFoodCardBalance(BigDecimal.TEN);
        return consumer;
    }

    @Test
    void buyFood() {

        Consumer consumer = getConsumer();
        when(consumerRepository.findByFoodCardNumber("888")).thenReturn(Optional.of(consumer));
        consumerService.buy(1, "teste","888","teste",BigDecimal.TEN);

        Consumer updatedConsumer = getConsumer();
        updatedConsumer.setFoodCardBalance(BigDecimal.valueOf(1.0));

        verify(consumerRepository, times(1)).save(updatedConsumer);
        verify(extractRepository, times(1)).save(any());

    }

    @Test
    void buyDrugStore() {

        Consumer consumer = getConsumer();
        when(consumerRepository.findByDrugStoreNumber("777")).thenReturn(Optional.of(consumer));
        consumerService.buy(2, "teste","777","teste",BigDecimal.TEN);

        Consumer updatedConsumer = getConsumer();
        updatedConsumer.setDrugStoreCardBalance(BigDecimal.ZERO);

        verify(consumerRepository, times(1)).save(updatedConsumer);
        verify(extractRepository, times(1)).save(any());

    }
}