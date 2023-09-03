package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExtractFuelServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ExtractRepository extractRepository;

    private ExtractFuelService extractService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        extractService = new ExtractFuelService(cardRepository, extractRepository);
    }

    @Test
    public void testBuyWithSufficientBalance() throws BusinessSaldoException {
        // Configurar o comportamento esperado do repositório de cartão (CardRepository)
        Integer cardNumber = 123456789;
        double cardBalance = 100.0;
        Card mockCard = new Card();
        mockCard.setCardNumber(cardNumber);
        mockCard.setCardBalance(cardBalance);
        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.of(mockCard));

        // Configurar um DTO para a compra
        ExtractDTO dto = new ExtractDTO();
        dto.setCardNumber(cardNumber);
        double purchaseValue = 50.0;
        dto.setValue(purchaseValue);
        dto.setProductDescription("Test Fuel Purchase");

        // Executar a compra
        extractService.buy(dto);

        // Verificar se o saldo do cartão foi atualizado corretamente (considerando a taxa)
        double expectedCardBalance = cardBalance - (purchaseValue + (purchaseValue * 35 / 100));
        assertEquals(expectedCardBalance, mockCard.getCardBalance());

        // Verificar se um registro de extrato foi salvo corretamente
        verify(extractRepository, times(1)).save(any(Extract.class));
    }

    @Test
    public void testBuyWithInsufficientBalance() {
        // Configurar o comportamento esperado do repositório de cartão (CardRepository)
        Integer cardNumber = 987654321;
        double cardBalance = 30.0;
        Card mockCard = new Card();
        mockCard.setCardNumber(cardNumber);
        mockCard.setCardBalance(cardBalance);
        when(cardRepository.findCardByCardNumber(cardNumber)).thenReturn(Optional.of(mockCard));

        // Configurar um DTO para a compra com valor maior do que o saldo
        ExtractDTO dto = new ExtractDTO();
        dto.setCardNumber(cardNumber);
        double purchaseValue = 100.0;
        dto.setValue(purchaseValue);
        dto.setProductDescription("Test Fuel Purchase");

        // Executar a compra e verificar se uma exceção BusinessSaldoException é lançada
        assertThrows(BusinessSaldoException.class, () -> extractService.buy(dto));

        // Verificar se o saldo do cartão não foi atualizado
        assertEquals(cardBalance, mockCard.getCardBalance());

        // Verificar se nenhum registro de extrato foi salvo
        verify(extractRepository, never()).save(any(Extract.class));
    }
}

