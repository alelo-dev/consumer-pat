package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CardMapperTest {

    private final Integer CARD_NUMBER = 1;
    private final Integer CARD_NUMBER_DTO = 22;
    private final Integer CONSUMER_ID = 1;
    private final String ENTITY_NAME = "Entity name for test";
    private final String ENTITY_DTO_NAME= "Entity DTO name for test";

    @InjectMocks
    private CardMapper mapper;

    @Mock
    private CardDTO dto;

    @Mock
    private Card entity;

    @Mock
    private Consumer consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        BigDecimal balanceValue = BigDecimal.valueOf(3000.00);
        when(consumer.getId()).thenReturn(CONSUMER_ID);

        when(dto.getCardNumber()).thenReturn(CARD_NUMBER_DTO);
        when(dto.getBalanceValue()).thenReturn(balanceValue);
        when(dto.getConsumer()).thenReturn(consumer);
        when(dto.getCardType()).thenReturn(CardType.FUEL);

        when(entity.getCardNumber()).thenReturn(CARD_NUMBER);
        when(entity.getBalanceValue()).thenReturn(balanceValue);
        when(entity.getCardType()).thenReturn(CardType.FOOD);
        when(entity.getConsumer()).thenReturn(consumer);
    }

    @Test
    void entityToDTO() {
        CardDTO dto = mapper.entityToDTO(entity);
        assertEquals(CARD_NUMBER, dto.getCardNumber());
        assertEquals(CONSUMER_ID,  dto.getConsumer().getId());
    }

    @Test
    void dtoToEntity () {
        when(dto.getConsumer().getName()).thenReturn(ENTITY_DTO_NAME);
        Card entity = mapper.dtoToEntity(dto);
        assertEquals(CARD_NUMBER_DTO, entity.getCardNumber());
        assertEquals(ENTITY_DTO_NAME,  entity.getConsumer().getName());
        assertEquals(CONSUMER_ID,  entity.getConsumer().getId());
    }

    @Test
    void newEntityToDTO() {
        CardCreateResponseDTO dto = mapper.newEntityToDTO(entity);
        assertEquals(CARD_NUMBER, dto.getCardNumber());
        assertEquals(CardType.FOOD,  dto.getCardType());
    }
}