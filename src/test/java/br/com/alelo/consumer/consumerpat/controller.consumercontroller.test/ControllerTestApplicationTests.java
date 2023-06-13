package controller.consumercontroller.test;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTestApplicationTests {

    @Mock
    private ConsumerRepository consumerRepository;

    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    private ConsumerController consumerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        consumers.add(consumer1);
        consumers.add(consumer2);

        when(consumerRepository.findAll()).thenReturn(consumers);

        List<Consumer> result = consumerController.listAllConsumers();

        verify(consumerRepository, times(1)).findAll();
        assertSame(consumers, result);
    }

    @Test
    void testCreateConsumer() {
        Consumer consumer = new Consumer();

        consumerController.createConsumer(consumer);

        verify(consumerRepository, times(1)).save(consumer);
    }

    @Test
    void testUpdateConsumer() {
        Consumer consumer = new Consumer();

        consumerController.updateConsumer(consumer);

        verify(consumerRepository, times(1)).save(consumer);
    }

    @Test
    void testSetBalance_withValidCardNumber_shouldUpdateConsumerBalance() {
        int cardNumber = 123456;
        double value = 100.0;
        Consumer consumer = new Consumer();
        consumer.setFoodCardBalance(50.0);
        Optional<Consumer> consumerOptional = Optional.of(consumer);

        when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(consumerOptional);

        consumerController.setBalance(cardNumber, value);

        verify(consumerRepository, times(1)).save(consumer);
        assertEquals(150.0, consumer.getFoodCardBalance(), 0.01);
    }

    @Test
    void testSetBalance_withInvalidCardNumber_shouldNotUpdateConsumerBalance() {
        int cardNumber = 123456;
        double value = 100.0;
        Optional<Consumer> consumerOptional = Optional.empty();

        when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(consumerOptional);

        consumerController.setBalance(cardNumber, value);

        verify(consumerRepository, never()).save(any(Consumer.class));
    }

    @Test
    void testBuy_withFoodEstablishmentType_shouldUpdateConsumerBalanceAndSaveExtract() {
        int establishmentType = 1;
        String establishmentName = "Food Establishment";
        int cardNumber = 123456;
        String productDescription = "Product";
        double value = 50.0;
        Consumer consumer = new Consumer();
        consumer.setFoodCardBalance(100.0);
        Optional<Consumer> consumerOptional = Optional.of(consumer);

        when(consumerRepository.findByFoodCardNumber(cardNumber)).thenReturn(consumerOptional);

        consumerController.buy(establishmentType, establishmentName, cardNumber, productDescription, value);

        verify(consumerRepository, times(1)).save(consumer);
        verify(extractRepository, times(1)).save(any(Extract.class));
        assertEquals(55.0, consumer.getFoodCardBalance(), 0.01);
    }

    @Test
    void testBuy_withDrugstoreEstablishmentType_shouldUpdateConsumerBalanceAndSaveExtract() {
        int establishmentType = 2;
        String establishmentName = "Drugstore";
        int cardNumber = 123456;
        String productDescription = "Product";
        double value = 50.0;
        Consumer consumer = new Consumer();
        consumer.setDrugstoreCardBalance(100.0);
        Optional<Consumer> consumerOptional = Optional.of(consumer);

        when(consumerRepository.findByDrugstoreNumber(cardNumber)).thenReturn(consumerOptional);

        consumerController.buy(establishmentType, establishmentName, cardNumber, productDescription, value);

        verify(consumerRepository, times(1)).save(consumer);
        verify(extractRepository, times(1)).save(any(Extract.class));
        assertEquals(50.0, consumer.getDrugstoreCardBalance(), 0.01);
    }

    @Test
    void testBuy_withFuelEstablishmentType_shouldUpdateConsumerBalanceAndSaveExtract() {
        int establishmentType = 3;
        String establishmentName = "Fuel Station";
        int cardNumber = 123456;
        String productDescription = "Product";
        double value = 50.0;
        Consumer consumer = new Consumer();
        consumer.setFuelCardBalance(100.0);
        Optional<Consumer> consumerOptional = Optional.of(consumer);

        when(consumerRepository.findByFuelCardNumber(cardNumber)).thenReturn(consumerOptional);

        consumerController.buy(establishmentType, establishmentName, cardNumber, productDescription, value);

        verify(consumerRepository, times(1)).save(consumer);
        verify(extractRepository, times(1)).save(any(Extract.class));
        assertEquals(32.5, consumer.getFuelCardBalance(), 0.01);
    }
}