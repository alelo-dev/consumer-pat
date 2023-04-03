package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.exception.ApiException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.model.CardEntityBuilder;
import br.com.alelo.consumer.consumerpat.model.SettlingEntityBuilder;
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
    void testAddBalanceForCardSuccess() throws ApiException {
        when(cardRepository.findByNumber(any())).thenReturn(CardEntityBuilder.cardBuilder());
        when(cardRepository.save(any())).thenReturn(CardEntityBuilder.cardBuilder());
        when(mapper.toResponse(any())).thenReturn(CardEntityBuilder.cardResponseBuilder());

        CardResponse cardResponse = cardService.updateBalance("21349182048129104", BigDecimal.valueOf(200));

        assertNotNull(cardResponse);
    }

    @Test
    void testPurchaseSuccess() throws ApiException {
        when(cardRepository.findByNumber(any())).thenReturn(CardEntityBuilder.cardBuilder());
        when(cardRepository.save(any())).thenReturn(CardEntityBuilder.cardBuilder());
        when(mapper.toResponse(any())).thenReturn(CardEntityBuilder.cardResponseBuilder());

        CardResponse cardResponse = cardService.updateSettlement(SettlingEntityBuilder.settlingRequestBuilder());

        assertNotNull(cardResponse);
    }

}