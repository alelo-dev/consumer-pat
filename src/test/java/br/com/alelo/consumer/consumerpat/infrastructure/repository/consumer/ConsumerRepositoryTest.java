package br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.entity.ConsumerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsumerRepositoryTest {

    @Mock
    private ConsumerJpaRepository consumerJpaRepository;

    @Mock
    private ConsumerConverter consumerConverter;

    @InjectMocks
    private ConsumerRepository consumerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        ConsumerEntity consumerEntity = createMockConsumerEntity(id);
        Consumer consumer = createMockConsumer(id);

        when(consumerJpaRepository.findById(id)).thenReturn(Optional.of(consumerEntity));
        when(consumerConverter.toDomain(consumerEntity)).thenReturn(consumer);

        Optional<Consumer> result = consumerRepository.findById(id);

        assertEquals(Optional.of(consumer), result);
        verify(consumerJpaRepository).findById(id);
        verify(consumerConverter).toDomain(consumerEntity);
    }

    @Test
    void testSave_NewConsumer() {
        UUID id = UUID.randomUUID();
        Consumer consumer = createMockConsumer(id);
        ConsumerEntity consumerEntity = createMockConsumerEntity(id);

        when(consumerConverter.toEntity(consumer)).thenReturn(consumerEntity);
        when(consumerJpaRepository.save(consumerEntity)).thenReturn(consumerEntity);

        UUID savedId = consumerRepository.save(consumer, true);

        assertEquals(id, savedId);
        verify(consumerConverter).toEntity(consumer);
        verify(consumerJpaRepository).save(consumerEntity);
    }

    @Test
    void testSave_ExistingConsumer() {
        UUID id = UUID.randomUUID();
        Consumer consumer = createMockConsumer(id);
        ConsumerEntity consumerEntity = createMockConsumerEntity(id);

        when(consumerConverter.toEntity(consumer)).thenReturn(consumerEntity);
        when(consumerJpaRepository.save(consumerEntity)).thenReturn(consumerEntity);

        UUID savedId = consumerRepository.save(consumer, false);

        assertEquals(id, savedId);
        verify(consumerConverter).toEntity(consumer);
        verify(consumerJpaRepository).save(consumerEntity);
        assertEquals(consumerEntity.getUpdatedAt(), consumerEntity.getUpdatedAt());
    }

    @Test
    void testListAll() {
        List<ConsumerEntity> consumerEntities = new ArrayList<>();
        consumerEntities.add(createMockConsumerEntity(UUID.randomUUID()));
        consumerEntities.add(createMockConsumerEntity(UUID.randomUUID()));

        Pageable pageable = PageRequest.of(0, 10);
        Page<ConsumerEntity> consumerEntityPage = new PageImpl<>(consumerEntities, pageable, consumerEntities.size());

        when(consumerJpaRepository.findAll(pageable)).thenReturn(consumerEntityPage);

        List<Consumer> expectedConsumers = new ArrayList<>();
        for (ConsumerEntity entity : consumerEntities) {
            Consumer consumer = createMockConsumer(entity.getId());
            expectedConsumers.add(consumer);
            when(consumerConverter.toDomain(entity)).thenReturn(consumer);
        }

        Page<Consumer> result = consumerRepository.listAll(pageable);

        assertEquals(expectedConsumers, result.getContent());
        assertEquals(consumerEntities.size(), result.getTotalElements());
        verify(consumerJpaRepository).findAll(pageable);
        verify(consumerConverter, times(consumerEntities.size())).toDomain(any(ConsumerEntity.class));
    }

    // Helper method to create a mock ConsumerEntity
    private ConsumerEntity createMockConsumerEntity(UUID id) {
        return ConsumerEntity.builder()
                .id(id)
                .name("John Doe")
                .documentNumber("1234567890")
                .birthDate(LocalDate.of(1980, 1, 1))
                .mobilePhoneNumber("123456789")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // Helper method to create a mock Consumer
    private Consumer createMockConsumer(UUID id) {
        Contact contact = new Contact("123456789", "987654321", "111222333", "john.doe@example.com");
        Address address = new Address("Street 1", 123, "City", "Country", "12345");
        return new Consumer("John Doe", "1234567890", LocalDate.of(1980, 1, 1), contact, address);
    }
}
