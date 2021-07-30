package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.MockitoBaseTest;
import br.com.alelo.consumer.consumerpat.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.util.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.card1;
import static br.com.alelo.consumer.consumerpat.builder.IncreaseCardBalanceBuilder.fullIncreaseCardBalanceDTO;
import static br.com.alelo.consumer.consumerpat.builder.TransactionBuilder.fullTransactionDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardServiceTest extends MockitoBaseTest {
    @Mock
    private CardRepository cardRepository;
    @Mock
    private ExtractService extractService;
    @Mock
    private TransactionOperationStrategy transactionOperationStrategy;
    @InjectMocks
    private CardService target;

    @Captor
    private ArgumentCaptor<Card> cardCaptor;

    @Test
    public void shouldThrowNotFoundExceptionWhenNoCardNumber() {
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> target.findCardByNumber(null),
                "Expected findCardByNumber() to throw, but it didn't"
        );
        assertTrue(thrown.getDetails().contains(Constants.CARD_NOT_FOUND));
    }

    @Test
    public void shouldIncreaseCardBalance() {
        IncreaseCardBalanceDTO request = fullIncreaseCardBalanceDTO();

        final Card bdCard = card1();
        when(cardRepository.findOneByNumber(request.getNumber())).thenReturn(Optional.of(bdCard));
        final BigDecimal expectedValue = request.getValue().add(bdCard.getBalance());

        target.increaseCardBalance(request);

        verify(cardRepository).save(cardCaptor.capture());
        Card capturedCard = cardCaptor.getValue();
        assertEquals(expectedValue, capturedCard.getBalance());
    }

    @Test
    public void shouldProcess() {
        TransactionDTO request = fullTransactionDTO();
        final Card bdCard = card1();
        when(cardRepository.findOneByNumber(request.getCardNumber())).thenReturn(Optional.of(bdCard));

        final BigDecimal value = BigDecimal.ONE;
        when(transactionOperationStrategy.calculate(any(), any())).thenReturn(value);
        final BigDecimal expectedValue = bdCard.getBalance().subtract(value);

        target.process(request);

        verify(cardRepository).save(cardCaptor.capture());
        Card capturedCard = cardCaptor.getValue();
        assertEquals(expectedValue, capturedCard.getBalance());
    }

}