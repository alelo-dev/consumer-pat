package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.excepion.CardNotFoundExeption;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ConsumerServiceTest {

    private ConsumerRepository consumerRepository;
    private ConsumerService service;

    ConsumerServiceTest() {
        consumerRepository = mock(ConsumerRepository.class);
        service = new ConsumerService(consumerRepository);
    }
    @Test
    public void shouldThrowExeptionWhenTryToUpdateConsumerWhithDiferentBalance() {
        Consumer consumerFind =  Consumer.builder().id(1).foodCardBalance(10.0).drugstoreCardBalance(10.0).fuelCardBalance(10.0).build();
        when(consumerRepository.findById(anyInt())).thenReturn(Optional.of(consumerFind));

        Consumer consumerToUpdate =  Consumer.builder().id(1).foodCardBalance(20.0).drugstoreCardBalance(10.0).fuelCardBalance(10.0).build();
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(consumerToUpdate);
        });
    }

    @Test
    public void shouldUpdateWithSucessWhenValidInfo() {
        Consumer consumerFind =  Consumer.builder().id(1).foodCardBalance(10.0).drugstoreCardBalance(10.0).fuelCardBalance(10.0).build();

        Consumer consumerToUpdate =  Consumer.builder().id(1).foodCardBalance(10.0).drugstoreCardBalance(10.0).fuelCardBalance(10.0).build();
        when(consumerRepository.findById(any(Integer.class))).thenReturn(Optional.of(consumerFind));
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumerFind);

        service.update(consumerToUpdate);
        verify(consumerRepository).save(any(Consumer.class));
    }

    @Test
    public void shouldInsertConsumerWithSucess() {
        Consumer consumerToUpdate =  Consumer.builder().foodCardBalance(20.0).build();

        Consumer consumerFind =  Consumer.builder().foodCardBalance(10.0).build();
        when(consumerRepository.findById(anyInt())).thenReturn(Optional.of(consumerFind));
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumerToUpdate);

        service.insert(consumerToUpdate);

        verify(consumerRepository).save(any(Consumer.class));
    }

}
