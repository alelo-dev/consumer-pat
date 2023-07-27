package br.com.alelo.consumer.consumerpat.domain.consumer.service;

import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.consumer.repository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsumerServiceTest {

    @Test
    void testCreateConsumer() {
        ConsumerRepository consumerRepository = mock(ConsumerRepository.class);

        String name = "John Doe";
        String documentNumber = "123456789";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Contact contact = new Contact("91234567890", "1234567890", "1234567890", "john.doe@example.com");
        Address address = new Address("Main Street", 123, "City", "Country", "12345");
        Consumer consumer = new Consumer(name, documentNumber, birthDate, contact, address);

        DomainConsumerService consumerService = new DomainConsumerService(consumerRepository);

        UUID createdConsumerId = consumerService.createConsumer(consumer);

        assertNotNull(createdConsumerId, "Created consumer ID should not be null");

        ArgumentCaptor<Consumer> consumerCaptor = ArgumentCaptor.forClass(Consumer.class);
        verify(consumerRepository).save(consumerCaptor.capture());

        Consumer capturedConsumer = consumerCaptor.getValue();

        assertEquals(name, capturedConsumer.getName(), "Name should match");
        assertEquals(documentNumber, capturedConsumer.getDocumentNumber(), "Document number should match");
        assertEquals(birthDate, capturedConsumer.getBirthDate(), "Birth date should match");
        assertEquals(contact, capturedConsumer.getContact(), "Contact should match");
        assertEquals(address, capturedConsumer.getAddress(), "Address should match");
    }


    @Test
    void testUpdateConsumer() {
        ConsumerRepository consumerRepository = mock(ConsumerRepository.class);
        UUID existingConsumerId = UUID.randomUUID();
        Consumer existingConsumer = new Consumer("John Doe", "123456789", LocalDate.of(1990, 1, 1), any(), any());
        existingConsumer.addId(existingConsumerId);
        Consumer updateConsumer = new Consumer("Updated Name", "987654321", LocalDate.of(1995, 5, 5), any(), any());

        when(consumerRepository.findById(existingConsumerId)).thenReturn(Optional.of(existingConsumer));

        DomainConsumerService consumerService = new DomainConsumerService(consumerRepository);

        consumerService.updateConsumer(existingConsumerId, updateConsumer);

        verify(consumerRepository, times(1)).findById(existingConsumerId);
        verify(consumerRepository, times(1)).save(any(Consumer.class));
    }

    @Test
    void testUpdateConsumerNotFound() {
        ConsumerRepository consumerRepository = mock(ConsumerRepository.class);

        UUID nonExistentConsumerId = UUID.randomUUID();
        Consumer updateConsumer = new Consumer("Updated Name", "987654321", LocalDate.of(1995, 5, 5), any(), any());

        when(consumerRepository.findById(nonExistentConsumerId)).thenReturn(Optional.empty());

        DomainConsumerService consumerService = new DomainConsumerService(consumerRepository);

        assertThrows(DomainException.class, () -> consumerService.updateConsumer(nonExistentConsumerId, updateConsumer));

        verify(consumerRepository, times(1)).findById(nonExistentConsumerId);
        verify(consumerRepository, never()).save(any(Consumer.class));
    }

    @Test
    void testSearchConsumerById() {
        ConsumerRepository consumerRepository = mock(ConsumerRepository.class);

        UUID existingConsumerId = UUID.randomUUID();
        Consumer existingConsumer = new Consumer("John Doe", "123456789", LocalDate.of(1990, 1, 1), any(), any());
        existingConsumer.addId(existingConsumerId);

        when(consumerRepository.findById(existingConsumerId)).thenReturn(Optional.of(existingConsumer));

        DomainConsumerService consumerService = new DomainConsumerService(consumerRepository);

        Optional<Consumer> foundConsumer = consumerService.searchConsumerById(existingConsumerId);

        assertTrue(foundConsumer.isPresent(), "Found consumer should be present");
        assertEquals(existingConsumer, foundConsumer.get(), "Found consumer should match the existing consumer");
        verify(consumerRepository, times(1)).findById(existingConsumerId);
    }

    @Test
    void testSearchConsumerByIdNotFound() {
        ConsumerRepository consumerRepository = mock(ConsumerRepository.class);

        UUID nonExistentConsumerId = UUID.randomUUID();

        when(consumerRepository.findById(nonExistentConsumerId)).thenReturn(Optional.empty());

        DomainConsumerService consumerService = new DomainConsumerService(consumerRepository);

        Optional<Consumer> foundConsumer = consumerService.searchConsumerById(nonExistentConsumerId);

        assertFalse(foundConsumer.isPresent(), "Non-existent consumer should not be found");
        verify(consumerRepository, times(1)).findById(nonExistentConsumerId);
    }
}
