package br.com.alelo.consumer.consumerpat.controller;

import org.junit.jupiter.api.BeforeEach;
import br.com.alelo.consumer.consumerpat.service.CardCreditService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CardsCreditControllerTest {

    @InjectMocks
    private CardsCreditController cardsCreditController;

    @Mock
    private CardCreditService cardCreditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetBalance_Success() {
        // Simula o comportamento do cardCreditService para não lançar exceções
        doNothing().when(cardCreditService).setBalance(anyInt(), anyDouble());

        ResponseEntity<Void> response = cardsCreditController.setBalance(123456789, 50.0);

        // Verifica se o método setBalance foi chamado com os argumentos corretos
        verify(cardCreditService).setBalance(123456789, 50.0);

        // Verifica se a resposta HTTP retornada é 200 (OK)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSetBalance_NotFound() {
        // Simula o comportamento do cardCreditService para lançar uma exceção NotFound
        doThrow(ChangeSetPersister.NotFoundException.class).when(cardCreditService).setBalance(anyInt(), anyDouble());

        ResponseEntity<Void> response = cardsCreditController.setBalance(987654321, 30.0);

        // Verifica se o método setBalance foi chamado com os argumentos corretos
        verify(cardCreditService).setBalance(987654321, 30.0);

        // Verifica se a resposta HTTP retornada é 404 (NOT FOUND)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}