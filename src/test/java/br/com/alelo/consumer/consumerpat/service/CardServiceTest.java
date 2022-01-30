package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mock.*;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.entity.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardServiceTest {


    private final Integer ID_CONSUMER = 1;
    private final Integer CARD_ID = 2;
    private final Integer CARD_NUMBER = 12345678;
    private final Integer CARD_NUMBER_NEW = 123456789;
    private final Integer DOCUMENT_NUMBER = 123456789;
    private final BigDecimal BALANCE_CARD = BigDecimal.valueOf(3000.00);


    @Mock
    private CardService service;
    @Mock
    private CardRepository repository;

    @Autowired
    private CardMapper mapper;
    @Mock
    private Card cardMock;
    @Mock
    private CardDTO cardDTOMock;

    @Mock
    private Consumer consumer;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(this.consumer.getId()).thenReturn(ID_CONSUMER);
        when(this.consumer.getDocumentNumber()).thenReturn(DOCUMENT_NUMBER);

        when(this.cardMock.getCardType()).thenReturn(CardType.FOOD);
        when(this.cardMock.getCardNumber()).thenReturn(CARD_NUMBER);
        when(this.cardMock.getBalanceValue()).thenReturn(BALANCE_CARD);
        when(this.cardMock.getConsumer()).thenReturn(consumer);

        when(this.cardDTOMock.getCardType()).thenReturn(CardType.FOOD);
        when(this.cardDTOMock.getCardNumber()).thenReturn(CARD_NUMBER);
        when(this.cardDTOMock.getBalanceValue()).thenReturn(BALANCE_CARD);
        when(this.cardDTOMock.getConsumer()).thenReturn(consumer);

        when(repository.save(cardMock)).thenReturn(cardMock);
    }

    @Test
    void save() throws BusinessException {

        CardCreateResponseDTO card =  CardCreateResponseDTO.builder()
                .cardType(cardDTOMock.getCardType())
                .cardNumber(cardDTOMock.getCardNumber())
                .build();
        when(service.save(cardDTOMock)).thenReturn(card);

        assertEquals(CARD_NUMBER, card.getCardNumber());
    }

    @Test
    void shold_findByCardNumber() {
        when(repository.findByCardNumber(CARD_NUMBER)).thenReturn(cardMock);
        var cardFind = service.findByCardNumber(CARD_NUMBER);
        assertEquals(CARD_NUMBER, cardFind.getCardNumber());
    }

    @Test
    void shold_findByCardNumber_not_found() {
        EntityNotFoundException exception =  assertThrows(EntityNotFoundException.class, () -> service.findByCardNumber(-1));
        assertEquals("Não foi encontrado um Cartão para o número informado", exception.getMessage());
    }




    @Test
    void debtByCard() {
    }

    @Test
    void credit() {
    }

    @Test
    void creditByCard() {
    }
}