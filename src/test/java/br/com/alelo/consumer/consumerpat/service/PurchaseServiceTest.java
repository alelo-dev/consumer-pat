package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.config.Messages;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Statement;
import br.com.alelo.consumer.consumerpat.entity.enumeration.PurchaseType;
import br.com.alelo.consumer.consumerpat.parameter.PurchaseParameter;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.StatementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {PurchaseService.class, Messages.class})
class PurchaseServiceTest {

    @Autowired
    PurchaseService service;

    @Autowired
    Messages messages;

    @MockBean
    StatementRepository statementRepository;

    @MockBean
    CardRepository cardRepository;

    @MockBean
    ConsumerService consumerService;

    @Test
    void buyShouldWorkIfEverythingIsOK() {
        Card card = new Card();
        card.setBalance(BigDecimal.valueOf(1000));
        card.setNumber("090909");
        card.setType(PurchaseType.FOOD);

        PurchaseParameter parameter = new PurchaseParameter();
        parameter.setConsumerId(1);
        parameter.setCardNumber(card.getNumber());
        parameter.setProductValue(BigDecimal.valueOf(900));
        parameter.setType(PurchaseType.FOOD);

        when(consumerService.existsConsumerById(parameter.getConsumerId())).thenReturn(true);
        when(consumerService.getCardOrException(parameter.getConsumerId(), parameter.getCardNumber())).thenReturn(card);

        service.buy(parameter);

        verify(cardRepository, times(1)).save(card);
        verify(statementRepository, times(1)).save(any(Statement.class));
    }

    @Test
    void buyShouldThrowEntityNotFoundExceptionIfConsumerNotFound() {
        PurchaseParameter parameter = new PurchaseParameter();
        parameter.setConsumerId(-1);

        when(consumerService.existsConsumerById(parameter.getConsumerId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.buy(parameter));
    }

    @Test
    void buyShouldThrowEntityNotFoundExceptionIfCardNumberNotFound() {
        PurchaseParameter parameter = new PurchaseParameter();
        parameter.setConsumerId(1);
        parameter.setCardNumber("0101");

        when(consumerService.existsConsumerById(parameter.getConsumerId())).thenReturn(true);
        when(consumerService.getCardOrException(parameter.getConsumerId(), parameter.getCardNumber()))
        .thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> service.buy(parameter));
    }

    @Test
    void buyShouldThrowIllegalArgumentExceptionIfInsufficientBalance() {
        PurchaseParameter parameter = new PurchaseParameter();
        parameter.setConsumerId(1);
        parameter.setCardNumber("0101");
        parameter.setProductValue(BigDecimal.TEN);
        parameter.setType(PurchaseType.FOOD);

        Card card = new Card();
        card.setBalance(BigDecimal.ONE);
        card.setNumber(parameter.getCardNumber());
        card.setType(parameter.getType());

        when(consumerService.existsConsumerById(parameter.getConsumerId())).thenReturn(true);
        when(consumerService.getCardOrException(parameter.getConsumerId(), parameter.getCardNumber())).thenReturn(card);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.buy(parameter));
        assertEquals(messages.insufficientBalance, exception.getMessage());
    }

    @Test
    void buyShouldThrowIllegalArgumentExceptionIfWrongPurchaseType() {
        PurchaseParameter parameter = new PurchaseParameter();
        parameter.setConsumerId(1);
        parameter.setCardNumber("0101");
        parameter.setType(PurchaseType.FOOD);

        Card card = new Card();
        card.setType(PurchaseType.DRUGSTORE);

        when(consumerService.existsConsumerById(parameter.getConsumerId())).thenReturn(true);
        when(consumerService.getCardOrException(parameter.getConsumerId(), parameter.getCardNumber())).thenReturn(card);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.buy(parameter));
        assertEquals(messages.invalidCardType, exception.getMessage());
    }

    @Test
    void getValidatedFinalProductValueShouldCalculateProperly() {
        for (PurchaseType type : PurchaseType.values()) {
            PurchaseParameter parameter = new PurchaseParameter();
            parameter.setType(type);
            parameter.setProductValue(BigDecimal.valueOf(100.0));

            Card card = new Card();
            card.setType(type);
            card.setBalance(BigDecimal.valueOf(1000.0));

            BigDecimal finalValue = service.getValidatedFinalProductValue(parameter, card);

            assertEquals(parameter.getProductValue().multiply(parameter.getType().getPercentageAdjustement()), finalValue);
        }
    }
}