package br.com.alelo.consumer.consumerpat.services;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.OperationException;
import br.com.alelo.consumer.consumerpat.exceptions.PurchaseException;
import br.com.alelo.consumer.consumerpat.helpers.purchase.DrugstoreEstablishmentPurchase;
import br.com.alelo.consumer.consumerpat.helpers.purchase.FoodEstablishmentPurchase;
import br.com.alelo.consumer.consumerpat.helpers.purchase.FuelEstablishmentPurchase;
import br.com.alelo.consumer.consumerpat.helpers.purchase.factories.PurchaseFactory;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.constants.TestConstants.getConsumer;
import static br.com.alelo.consumer.consumerpat.constants.TestConstants.getExtract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ConsumerServiceTest {

    @Autowired
    private ConsumerService service;

    @MockBean
    private ConsumerRepository repository;

    @MockBean
    private ExtractRepository extractRepository;

    @MockBean
    private PurchaseFactory factory;

    @Test
    public void listAllConsumersShouldReturnAllConsumers() {
        //given
        List<Consumer> consumer = repository.findAll();

        //when
        when(repository.findAll())
                .thenReturn(Collections.singletonList(getConsumer()));

        //then
        List<Consumer> consumers = service.listAllConsumers();

        assertEquals(getConsumer().getName(), consumers.get(0).getName());
        assertEquals(getConsumer().getDocumentNumber(), consumers.get(0).getDocumentNumber());
        assertEquals(getConsumer().getCard(), consumers.get(0).getCard());
    }

    @Test
    public void createConsumerShouldReturnCreated() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(repository.save(any(Consumer.class)))
                .thenReturn(consumer);

        //then
        Consumer saved = service.createConsumer(consumer);

        assertNotNull(saved);
        assertEquals(consumer.getName(), saved.getName());
        assertEquals(consumer.getDocumentNumber(), saved.getDocumentNumber());
        assertEquals(consumer.getCard(), saved.getCard());
    }

    @Test
    public void updateConsumerShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(repository.findById(any()))
                .thenReturn(Optional.of(consumer));

        when(repository.save(any(Consumer.class)))
                .thenReturn(consumer);

        //then
        Consumer updated = service.updateConsumer(consumer);

        assertNotNull(updated);
    }

    @Test
    public void updateConsumerShouldReturnUnauthorized() {
        //given
        Consumer consumer = getConsumer();
        Consumer updated = getConsumer();

        //when
        when(repository.findById(any()))
                .thenReturn(Optional.of(consumer));

        when(repository.save(any(Consumer.class)))
                .thenReturn(consumer);

        //then
        assertThrows(PurchaseException.class, () -> {
            updated.getCard().setFoodCardBalance(100);
            service.updateConsumer(updated);
        });
    }

    @Test
    public void updateConsumerShouldReturnNotFound() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(PurchaseException.class, () -> {
            service.updateConsumer(consumer);
        });
    }

    @Test
    public void setCardBalanceShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Consumer updated = getConsumer();

        final double newBalance = 550;
        final double expectedBalance = consumer.getCard().getDrugstoreCardBalance() + newBalance;

        updated.getCard().setDrugstoreCardBalance(1050);

        //when
        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(repository.save(any(Consumer.class)))
                .thenReturn(updated);

        //then
        Consumer newConsumer = service
                .setCardBalance(consumer.getCard().getDrugstoreCardNumber(), newBalance);

        assertNotNull(newConsumer);
        assertEquals(expectedBalance, newConsumer.getCard().getDrugstoreCardBalance());
    }

    @Test
    public void setCardBalanceByFoodCardShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Consumer updated = getConsumer();

        final double newBalance = 550;
        final double expectedBalance = consumer.getCard().getDrugstoreCardBalance() + newBalance;

        updated.getCard().setDrugstoreCardBalance(1050);

        //when
        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        when(repository.findByCardFoodCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(repository.save(any(Consumer.class)))
                .thenReturn(updated);

        //then
        Consumer newConsumer = service
                .setCardBalance(consumer.getCard().getFoodCardNumber(), newBalance);

        assertNotNull(newConsumer);
        assertEquals(expectedBalance, newConsumer.getCard().getFoodCardBalance());
    }

    @Test
    public void setCardBalanceByFuelCardShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Consumer updated = getConsumer();

        final double newBalance = 550;
        final double expectedBalance = consumer.getCard().getDrugstoreCardBalance() + newBalance;

        updated.getCard().setDrugstoreCardBalance(1050);

        //when
        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        when(repository.findByCardFoodCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        when(repository.findByCardFuelCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(repository.save(any(Consumer.class)))
                .thenReturn(updated);

        //then
        Consumer newConsumer = service
                .setCardBalance(consumer.getCard().getFuelCardNumber(), newBalance);

        assertNotNull(newConsumer);
        assertEquals(expectedBalance, newConsumer.getCard().getFuelCardBalance());
    }

    @Test
    public void setCardBalanceShouldReturnNotFound() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        when(repository.findByCardFoodCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        when(repository.findByCardFuelCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(OperationException.class, () -> {
           service.setCardBalance(consumer.getCard().getDrugstoreCardNumber(),
                   consumer.getCard().getDrugstoreCardBalance());
        });
    }

    @Test
    public void buyWithFoodCardShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Extract extract = getExtract();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new FoodEstablishmentPurchase(repository));

        when(repository.findByCardFoodCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(extractRepository.save(any(Extract.class)))
                .thenReturn(extract);

        //then
        Extract saved = service.buy(1, "Name", consumer.getCard().getFoodCardNumber(), "Description", 50);

        assertNotNull(saved);
    }

    @Test
    public void buyWithFoodCardShouldReturnNotFound() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new FoodEstablishmentPurchase(repository));

        when(repository.findByCardFoodCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(PurchaseException.class, () -> {
            service.buy(1, "Name", consumer.getCard().getFoodCardNumber(), "Description", 50);
        });
    }

    @Test
    public void buyWithDrugstoreCardShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Extract extract = getExtract();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new DrugstoreEstablishmentPurchase(repository));

        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(extractRepository.save(any(Extract.class)))
                .thenReturn(extract);

        //then
        Extract saved = service.buy(1, "Name", consumer.getCard().getDrugstoreCardNumber(), "Description", 50);

        assertNotNull(saved);
    }

    @Test
    public void buyWithDrugstoreCardShouldReturnNotFound() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new DrugstoreEstablishmentPurchase(repository));

        when(repository.findByCardDrugstoreCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(PurchaseException.class, () -> {
            service.buy(1, "Name", consumer.getCard().getDrugstoreCardNumber(), "Description", 50);
        });
    }

    @Test
    public void buyWithFuelCardShouldReturnOk() {
        //given
        Consumer consumer = getConsumer();
        Extract extract = getExtract();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new FuelEstablishmentPurchase(repository));

        when(repository.findByCardFuelCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(extractRepository.save(any(Extract.class)))
                .thenReturn(extract);

        //then
        Extract saved = service.buy(1, "Name", consumer.getCard().getFuelCardNumber(), "Description", 50);

        assertNotNull(saved);
    }

    @Test
    public void buyWithFuelCardShouldReturnNotFound() {
        //given
        Consumer consumer = getConsumer();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new FuelEstablishmentPurchase(repository));

        when(repository.findByCardFuelCardNumber(anyInt()))
                .thenReturn(Optional.empty());

        //then
        assertThrows(PurchaseException.class, () -> {
            service.buy(1, "Name", consumer.getCard().getFuelCardNumber(), "Description", 50);
        });
    }

    @Test
    public void buyShouldReturnNotEnoughBalance() {
        //given
        Consumer consumer = getConsumer();
        Extract extract = getExtract();

        //when
        when(factory.findStrategy(anyInt()))
                .thenReturn(new FuelEstablishmentPurchase(repository));

        when(repository.findByCardFuelCardNumber(anyInt()))
                .thenReturn(Optional.of(consumer));

        when(extractRepository.save(any(Extract.class)))
                .thenReturn(extract);

        //then
        assertThrows(PurchaseException.class, () -> {
            service.buy(1, "Name", consumer.getCard().getFuelCardNumber(), "Description", 1000);
        });
    }

}
