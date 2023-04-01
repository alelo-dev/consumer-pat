package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.exception.InvalidCardException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.model.CardMockEntity;
import br.com.alelo.consumer.consumerpat.model.SettlingMockEntity;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
public class CardServiceTest {

    @Mock
    private CardMapper mapper;

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(cardService, "cardRepository", cardRepository);
    }

    @Test
    void testAddBalanceForCardSuccess() throws InvalidCardException {
        when(cardRepository.findByNumber(any())).thenReturn(CardMockEntity.cardBuilder());
        when(cardRepository.save(any())).thenReturn(CardMockEntity.cardBuilder());
        when(mapper.toResponse(any())).thenReturn(CardMockEntity.cardResponseBuilder());

        CardResponse cardResponse = cardService.updateBalance("21349182048129104", BigDecimal.valueOf(200));

        assertNotNull(cardResponse);
    }

    @Test
    void testPurchaseSuccess() throws InvalidCardException {
        when(cardRepository.findByNumber(any())).thenReturn(CardMockEntity.cardBuilder());
        when(cardRepository.save(any())).thenReturn(CardMockEntity.cardBuilder());
        when(mapper.toResponse(any())).thenReturn(CardMockEntity.cardResponseBuilder());

        CardResponse cardResponse = cardService.updateSettlement(SettlingMockEntity.settlingRequestBuilder());

        assertNotNull(cardResponse);
    }

}