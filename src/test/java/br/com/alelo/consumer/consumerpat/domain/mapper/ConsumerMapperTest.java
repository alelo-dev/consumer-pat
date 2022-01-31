package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.DataTesteUtils;
import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsumerMapperTest {

    private final Integer ENTITY_ID = 1;

    @InjectMocks
    private ConsumerMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void entityToDTO() {
        Consumer consumer = DataTesteUtils.getConsumer();
        consumer.setId(ENTITY_ID);
        ConsumerDTO dto = mapper.entityToDTO(consumer);
        assertEquals(ENTITY_ID, dto.getId());
        assertEquals(consumer.getDocumentNumber(), dto.getDocumentNumber());
        assertEquals(consumer.getCountry(), dto.getCountry());
        assertEquals(consumer.getEmail(), dto.getEmail());
        assertEquals(consumer.getBirthDate(), dto.getBirthDate());
    }

    @Test
    void dtoToEntity () {
        ConsumerDTO dto = DataTesteUtils.getConsumerDTO();
        Consumer consumer = mapper.dtoToEntity(dto);
        assertEquals(dto.getDocumentNumber(), consumer.getDocumentNumber());
        assertEquals(dto.getBirthDate(), consumer.getBirthDate());
        assertEquals(dto.getCountry(), consumer.getCountry());
        assertEquals(dto.getEmail(), consumer.getEmail());
    }
}