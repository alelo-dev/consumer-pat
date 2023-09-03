package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import br.com.alelo.consumer.consumerpat.exception.BusinessSaldoException;
import br.com.alelo.consumer.consumerpat.service.ExtractFactory;
import br.com.alelo.consumer.consumerpat.service.ExtractStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExtractControllerTest {

    @InjectMocks
    private ExtractController extractController;

    @Mock
    private ExtractFactory factory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuy_Success() throws BusinessSaldoException {
        // Simula um DTO de compra
        ExtractDTO dtoToBuy = new ExtractDTO();
        dtoToBuy.setId(1);
        dtoToBuy.setValue(100.0);
        dtoToBuy.setCardNumber(1234567897);
        dtoToBuy.setCompanyName("Food");
        dtoToBuy.setCompanyNameId(1);
        dtoToBuy.setDateBuy(new Date());

        // Simula uma estratégia de compra
        ExtractStrategy strategy = mock(ExtractStrategy.class);

        // Configura o comportamento do factory para retornar a estratégia
        when(factory.findStrategyByCompanyId(CompanyType.FOOD)).thenReturn(strategy);

        // Chama o método buy
        ResponseEntity<String> response = extractController.buy(dtoToBuy);

        // Verifica se a estratégia foi chamada corretamente
        verify(strategy, times(1)).buy(dtoToBuy);

        // Verifica se a resposta HTTP é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testBuy_BusinessSaldoException() throws BusinessSaldoException {
        // Simula um DTO de compra
        ExtractDTO dtoToBuy = new ExtractDTO();
        dtoToBuy.setId(1);
        dtoToBuy.setValue(100.0);
        dtoToBuy.setCardNumber(1234567897);
        dtoToBuy.setCompanyName("Fuel");
        dtoToBuy.setCompanyNameId(3);
        dtoToBuy.setDateBuy(new Date());

        // Simula uma estratégia de compra
        ExtractStrategy strategy = mock(ExtractStrategy.class);

        // Configura o comportamento do factory para retornar a estratégia
        when(factory.findStrategyByCompanyId(CompanyType.DRUGSTORE)).thenReturn(strategy);

        // Simula uma exceção de saldo insuficiente
        doThrow(new BusinessSaldoException("Saldo insuficiente")).when(strategy).buy(dtoToBuy);

        // Chama o método buy
        ResponseEntity<String> response = extractController.buy(dtoToBuy);

        // Verifica se a estratégia foi chamada corretamente
        verify(strategy, times(1)).buy(dtoToBuy);

        // Verifica se a resposta HTTP é BAD_REQUEST (400) com a mensagem de erro
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Saldo insuficiente", response.getBody());
    }
}
