package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.mock.MockConsumerDomain;
import br.com.alelo.consumer.consumerpat.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
public class ConsumerServiceTest {

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    @Mock
    private ConsumerRepository consumerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private ConsumerMapper consumerMapper;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(consumerService, "consumerRepository", consumerRepository);
    }


    @Test
    @DisplayName("ConsumerServiceTest Test create Consumer success")
    void testCreateConsumerSucess() throws BusinessException {

        when(consumerMapper.toEntity(any())).thenReturn(MockConsumerDomain.buildConsumer());
        when(consumerRepository.save(any())).thenReturn(MockConsumerDomain.buildConsumer());
        when(consumerMapper.toResponse(any())).thenReturn(MockConsumerDomain.buildConsumerResponse());


        ConsumerResponse consumer = consumerService.createConsumer(MockConsumerDomain.buildConsumerRequest());

        assertNotNull(consumer);
    }

    @Test
    @DisplayName("ConsumerServiceTest Test Create Card For Consumer By Id Not Found")
    void testcreateCardForConsumerByIdNotFound() throws BusinessException {

        when(consumerMapper.toEntity(any())).thenReturn(MockConsumerDomain.buildConsumer());
        when(consumerRepository.existsDocument(any())).thenReturn(true);

        BusinessException exception = assertThrows(BusinessException.class, () -> consumerService.createConsumer(MockConsumerDomain.buildConsumerRequest()));

        assertNotNull(exception);
        assertEquals("Document already exists in base", exception.getMessage());
    }

    @Test
    @DisplayName("ConsumerServiceTest Test List All Consumers Sucess")
    void testListAllConsumersSucess() throws BusinessException {

        List<Consumer> consumers = List.of(MockConsumerDomain.buildConsumer());
        PageImpl<Consumer> foundPageConsumer = new PageImpl<>(consumers);

        Pageable pageable = PageRequest.of(0, 10);

        when(consumerRepository.findAll(pageable)).thenReturn(foundPageConsumer);

        Page<ConsumerResponse> findAll = consumerService.listAllConsumers(pageable);

        assertNotNull(findAll);
        assertNotNull(findAll.getContent());
        assertEquals(1, findAll.getTotalElements());

    }

    @Test
    @DisplayName("ConsumerServiceTest Test update Consumer success")
    void testUpdateConsumerSucess() throws BusinessException {

        when(consumerRepository.findById(any())).thenReturn(Optional.of(MockConsumerDomain.buildConsumer()));
        when(consumerRepository.save(any())).thenReturn(MockConsumerDomain.buildConsumer());
        when(consumerMapper.toResponse(any())).thenReturn(MockConsumerDomain.buildConsumerResponse());


        ConsumerResponse consumer = consumerService.updateConsumer(1L, MockConsumerDomain.buildConsumerRequest());

        assertNotNull(consumer);
    }

    @Test
    @DisplayName("ConsumerServiceTest Test update Consumer Not Found")
    void testUpdateConsumerNotFound() throws BusinessException {

        when(consumerRepository.findById(any())).thenReturn(Optional.empty());

        BusinessException exception =
                assertThrows(BusinessException.class, () -> consumerService.updateConsumer(1L, MockConsumerDomain.buildConsumerRequest()));

        assertNotNull(exception);
        assertEquals("Consumer not found", exception.getMessage());

    }
}
