package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.ModelMapperConfig;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.mock.MockCardDomain;
import br.com.alelo.consumer.consumerpat.mock.MockConsumerDomain;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(classes = {ModelMapperConfig.class})
public class CardServiceTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private CardMapper cardMapper;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ConsumerRepository consumerRepository;



    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(cardService, "cardRepository", cardRepository);
    }


    @Test
    @DisplayName("CardServiceTest Test create Card For Consumer is success")
    void testcreateCardForConsumerSucess() throws BusinessException {

       when(consumerRepository.findById(any())).thenReturn(Optional.of(MockConsumerDomain.buildConsumer()));
       when(cardMapper.toEntity(any())).thenReturn(MockCardDomain.buildCard());
       when(cardRepository.save(any())).thenReturn(MockCardDomain.buildCard());
       when(cardMapper.toResponse(any())).thenReturn(MockCardDomain.buildCardResponse());


        CardResponse cardForConsumer = cardService.createCardForConsumer(MockCardDomain.buildCardRequest());

        assertNotNull(cardForConsumer);
    }

    @Test
    @DisplayName("CardServiceTest Test Create Card For Consumer By Id Not Found")
    void testcreateCardForConsumerByIdNotFound() throws BusinessException {

        when(consumerRepository.findById(any())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.createCardForConsumer(MockCardDomain.buildCardRequest()));

        assertNotNull(exception);
        assertEquals("Consumer not found", exception.getMessage());
    }

    @Test
    @DisplayName("CardServiceTest Test Create Card For Consumer Exists Card For Consumer")
    void testcreateCardForConsumerExistsCardForConsumer() throws BusinessException {

        when(consumerRepository.findById(any())).thenReturn(Optional.of(MockConsumerDomain.buildConsumerExistCard()));

        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.createCardForConsumer(MockCardDomain.buildCardRequestExistCard()));

        assertNotNull(exception);
        assertEquals("already contains a FOOD card linked to the consumer", exception.getMessage());
    }

    @Test
    @DisplayName("CardServiceTest Test Add Balance For Card is success")
    void testAddBalanceForCardSucess() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(cardRepository.save(any())).thenReturn(MockCardDomain.buildCard());
        when(cardMapper.toResponse(any())).thenReturn(MockCardDomain.buildCardResponse());

        CardResponse cardResponse = cardService.addBalanceForCard("9557596836147377", BigDecimal.valueOf(100));

        assertNotNull(cardResponse);
    }

    @Test
    @DisplayName("CardServiceTest Test Add Balance For Card Not Found")
    void testAddBalanceForCardNotFound() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.empty());


        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.addBalanceForCard("9557596836147377", BigDecimal.valueOf(100)));

        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }

    @Test
    @DisplayName("CardServiceTest Test Add Balance For Card Inactive Error")
    void testAddBalanceForCardInactiveError() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCardInactive()));


        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.addBalanceForCard("9557596836147377", BigDecimal.valueOf(100)));

        assertNotNull(exception);
        assertEquals("The card informed is not active", exception.getMessage());
    }


    @Test
    @DisplayName("CardServiceTest Test find Card Number success")
    void testFindCardNumber() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(cardMapper.toResponse(any())).thenReturn(MockCardDomain.buildCardResponse());

        CardResponse cardResponse = cardService.findCardNumber("9557596836147377");

        assertNotNull(cardResponse);
    }

    @Test
    @DisplayName("CardServiceTest Test find Card Number not found")
    void testFindCardNumberNotFound() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.findCardNumber("9557596836147377"));

        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }


    @Test
    @DisplayName("CardServiceTest Test Active Card true success")
    void testActiveTrueCard() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCardActiveFalse()));
        when(cardMapper.toResponse(any())).thenReturn(MockCardDomain.buildCardResponse());

        CardResponse cardResponse = cardService.activeCard("9557596836147377");

        assertNotNull(cardResponse);
        assertEquals(true, cardResponse.isActive());
    }

    @Test
    @DisplayName("CardServiceTest Test Active Card false success")
    void testActiveFalseCard() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.of(MockCardDomain.buildCard()));
        when(cardMapper.toResponse(any())).thenReturn(MockCardDomain.buildCardResponseActiveFalse());

        CardResponse cardResponse = cardService.activeCard("9557596836147377");

        assertNotNull(cardResponse);
        assertEquals(false, cardResponse.isActive());
    }

    @Test
    @DisplayName("CardServiceTest Test Active Card Not Found")
    void testActiveNotFound() throws BusinessException {

        when(cardRepository.findByCardNumber(any())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> cardService.activeCard("9557596836147377"));

        assertNotNull(exception);
        assertEquals("Card not found", exception.getMessage());
    }


}
