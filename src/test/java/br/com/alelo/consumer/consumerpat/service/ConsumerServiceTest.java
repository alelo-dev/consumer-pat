package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ApiException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.model.ConsumerMockEntity;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
public class ConsumerServiceTest {

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    @Mock
    private ConsumerRepository repository;

    @Mock
    private ConsumerMapper consumerMapper;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(consumerService, "repository", repository);
    }

    @Test
    void testListAllConsumersSuccess() {
        List<Consumer> consumers = List.of(ConsumerMockEntity.consumerBuilder());
        PageImpl<Consumer> foundPageConsumer = new PageImpl<>(consumers);

        Pageable pageable = PageRequest.of(0, 15);

        when(repository.findAll(pageable)).thenReturn(foundPageConsumer);
        Page<ConsumerResponse> findAll = consumerService.findAllConsumers(pageable);

        assertNotNull(findAll);
        assertNotNull(findAll.getContent());
        assertEquals(1, findAll.getTotalElements());
    }

    @Test
    void testCreateConsumerSuccess() throws ApiException {
        when(consumerMapper.toRequest(any())).thenReturn(ConsumerMockEntity.consumerBuilder());
        when(repository.save(any())).thenReturn(ConsumerMockEntity.consumerBuilder());
        when(consumerMapper.toResponse(any())).thenReturn(ConsumerMockEntity.consumerResponseBuilder());

        ConsumerResponse consumer = consumerService.save(ConsumerMockEntity.consumerRequestBuilder());

        assertNotNull(consumer);
    }

    @Test
    void testUpdateConsumerSuccess() {
        when(repository.findById(any())).thenReturn(Optional.of(ConsumerMockEntity.consumerBuilder()));
        when(repository.save(any())).thenReturn(ConsumerMockEntity.consumerBuilder());
        when(consumerMapper.toResponse(any())).thenReturn(ConsumerMockEntity.consumerResponseBuilder());

        ConsumerResponse consumer = consumerService.update(1, ConsumerMockEntity.consumerRequestBuilder());

        assertNotNull(consumer);
    }

    @Test
    void testUpdateConsumerNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> consumerService.update(1,
                        ConsumerMockEntity.consumerRequestBuilder()));

        assertNotNull(exception);
        assertEquals("Consumer Not Found", exception.getMessage());
    }
}
