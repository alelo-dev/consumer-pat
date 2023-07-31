package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.DomainConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainConsumerServiceTest {

    @Mock
    private DomainConsumerRepository consumerRepository;

    @InjectMocks
    private DomainConsumerService consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConsumer() {
        Consumer newConsumer = new Consumer(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );

        UUID generatedId = UUID.randomUUID();
        when(consumerRepository.save(newConsumer, true)).thenReturn(generatedId);

        UUID createdId = consumerService.createConsumer(newConsumer);
        assertEquals(generatedId, createdId);

        verify(consumerRepository, times(1)).save(newConsumer, true);
    }

    @Test
    void testUpdateConsumer() {
        UUID existingConsumerId = UUID.randomUUID();
        Consumer existingConsumer = new Consumer(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );
        existingConsumer.addId(existingConsumerId);

        Consumer updatedConsumer = new Consumer(
                "Updated John Doe",
                "987654321",
                LocalDate.of(1980, 2, 2),
                new Contact("987654321", "123456789", "999999999", "updated@example.com"),
                new Address("456 New St", 20, "City", "Country", "54321")
        );
        updatedConsumer.addId(existingConsumerId);

        when(consumerRepository.findById(existingConsumerId)).thenReturn(Optional.of(existingConsumer));
        when(consumerRepository.save(any(Consumer.class), eq(false))).thenReturn(existingConsumerId);

        consumerService.updateConsumer(existingConsumerId, updatedConsumer);

        verify(consumerRepository, times(1)).findById(existingConsumerId);

        verify(consumerRepository, times(1)).save(updatedConsumer, false);

        assertEquals(updatedConsumer.getName(), existingConsumer.getName());
        assertEquals(updatedConsumer.getDocumentNumber(), existingConsumer.getDocumentNumber());
        assertEquals(updatedConsumer.getBirthDate(), existingConsumer.getBirthDate());
        assertEquals(updatedConsumer.getContact(), existingConsumer.getContact());
        assertEquals(updatedConsumer.getAddress(), existingConsumer.getAddress());
    }

    @Test
    void testSearchConsumerById() {
        UUID consumerId = UUID.randomUUID();
        Consumer consumer = new Consumer(
                "John Doe",
                "123456789",
                LocalDate.of(1990, 1, 1),
                new Contact("123456789", "987654321", "111111111", "john@example.com"),
                new Address("123 Main St", 10, "City", "Country", "12345")
        );
        consumer.addId(consumerId);

        when(consumerRepository.findById(consumerId)).thenReturn(Optional.of(consumer));

        Optional<Consumer> foundConsumer = consumerService.searchConsumerById(consumerId);
        assertTrue(foundConsumer.isPresent());
        assertEquals(consumer, foundConsumer.get());

        verify(consumerRepository, times(1)).findById(consumerId);
    }

    @Test
    void testSearchConsumerById_NotFound() {
        UUID nonExistingConsumerId = UUID.randomUUID();

        when(consumerRepository.findById(nonExistingConsumerId)).thenReturn(Optional.empty());

        Optional<Consumer> foundConsumer = consumerService.searchConsumerById(nonExistingConsumerId);

        assertFalse(foundConsumer.isPresent());

        verify(consumerRepository, times(1)).findById(nonExistingConsumerId);
    }

    @Test
    void testListAllConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(new Consumer("John Doe", "123456789", LocalDate.of(1990, 1, 1), null, null));
        consumers.add(new Consumer("Jane Smith", "987654321", LocalDate.of(1985, 5, 10), null, null));

        Pageable pageable = Pageable.unpaged();
        Page<Consumer> consumerPage = new PageImpl<>(consumers, pageable, consumers.size());
        when(consumerRepository.listAll(pageable)).thenReturn(consumerPage);

        Page<Consumer> result = consumerService.listAll(pageable);

        assertEquals(consumers, result.getContent());

        verify(consumerRepository, times(1)).listAll(pageable);
    }
}

