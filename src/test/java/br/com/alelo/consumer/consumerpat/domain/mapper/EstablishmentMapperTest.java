package br.com.alelo.consumer.consumerpat.domain.mapper;

import br.com.alelo.consumer.consumerpat.domain.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Establishment;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EstablishmentMapperTest {

    private final Integer ENTITY_ID = 1;
    private final String ENTITY_NAME = "Entity name for test";
    private final String ENTITY_DTO_NAME= "Entity DTO name for test";


    @InjectMocks
    private EstablishmentMapper mapper;

    @Mock
    private EstablishmentDTO dto;

    @Mock
    private Establishment entity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime dataCriacao = LocalDateTime.of(2022,01,29,22,10);
        when(dto.getEstablishmentName()).thenReturn(ENTITY_DTO_NAME);
        when(dto.getCardTypeAccepted()).thenReturn(CardType.FOOD);
        when(dto.getCreateDate()).thenReturn(dataCriacao);


        when(entity.getEstablishmentName()).thenReturn(ENTITY_NAME);
        when(entity.getCardTypeAccepted()).thenReturn(CardType.FUEL);
        when(entity.getCreateDate()).thenReturn(dataCriacao);
        when(entity.getId()).thenReturn(ENTITY_ID);
    }


    @Test
    void entityToDTO() {
        EstablishmentDTO dto = mapper.entityToDTO(entity);
        assertEquals(ENTITY_NAME, dto.getEstablishmentName());
        assertEquals(CardType.FUEL, dto.getCardTypeAccepted());
    }

    @Test
    void dtoToEntity () {
        Establishment establishment = mapper.dtoToEntity(dto);
        assertEquals(ENTITY_DTO_NAME, dto.getEstablishmentName());
        assertEquals(CardType.FOOD, dto.getCardTypeAccepted());
    }

}