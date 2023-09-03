package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springdoc.core.ReturnTypeParser.getType;

public class ExtractDrugstoreServiceTest {

    @InjectMocks
    private ExtractDrugstoreService extractService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ExtractRepository extractRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuy_Success() throws BusinessSaldoException {
        // Simula um DTO de compra
        ExtractDTO dtoToBuy = new ExtractDTO();
        dtoToBuy.setCardNumber(123456);
        dtoToBuy.setValue(100.0);
        dtoToBuy.setProductDescription("Descrição do produto");


        // Simula um cartão existente com saldo
        Card card = new Card();
        card.setCardBalance(50.0);
        card.setCardNumber(123456789);
        card.setId(1);


        // Configura o comportamento do cardRepository para encontrar o cartão
        when(cardRepository.findCardByCardNumber(dtoToBuy.getCardNumber())).thenReturn(Optional.of(card));

        // Chama o método buy
        assertDoesNotThrow(() -> extractService.buy(dtoToBuy));

        // Verifica se o saldo do cartão foi atualizado corretamente
        assertEquals(50.0, card.getCardBalance());

        // Verifica se o extractRepository foi chamado para salvar a compra
        verify(extractRepository, times(1)).save(any(Extract.class));
    }

    @Test
    public void testBuy_InsufficientBalance() {
        // Simula um DTO de compra com saldo insuficiente
        ExtractDTO dtoToBuy = new ExtractDTO();
        dtoToBuy.setCardNumber(123456789);
        dtoToBuy.setId(1);
        dtoToBuy.setCompanyNameId(1);
        dtoToBuy.setValue(100.0);


        // Simula um cartão existente com saldo insuficiente
        Card card = new Card();
        card.setCardBalance(50.0);
        card.setId(1);
        card.setCardNumber(123456789);


        // Configura o comportamento do cardRepository para encontrar o cartão
        when(cardRepository.findCardByCardNumber(dtoToBuy.getCardNumber())).thenReturn(Optional.of(card));

        // Chama o método buy e verifica se uma exceção é lançada
        assertThrows(BusinessSaldoException.class, () -> extractService.buy(dtoToBuy));

        // Verifica se o saldo do cartão não foi alterado
        assertEquals(100.0, card.getCardBalance());

        // Verifica se o extractRepository não foi chamado para salvar a compra
        verify(extractRepository, never()).save(any(Extract.class));
    }
}


