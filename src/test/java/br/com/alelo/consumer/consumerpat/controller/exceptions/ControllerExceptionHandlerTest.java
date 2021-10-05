package br.com.alelo.consumer.consumerpat.controller.exceptions;

import br.com.alelo.consumer.consumerpat.services.exceptions.IllegalArgumentException;
import br.com.alelo.consumer.consumerpat.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ControllerExceptionHandlerTest {
    
    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnObjectNotFoundException() {
        ResponseEntity<StandardError> response = exceptionHandler.objectNotFound(
                        new ObjectNotFoundException("Objeto não encontrado"), new MockHttpServletRequest());
        
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void whenIllegalArgumentThenReturnIllegalArgumentException() {
        ResponseEntity<StandardError> response = exceptionHandler.illegalArgumen(
                new IllegalArgumentException("Cartão não valido para este estabelecimento"),
                new MockHttpServletRequest());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void whenDataIntegrityViolationThenReturnDataIntegrityViolation() {
        ResponseEntity<StandardError> response = exceptionHandler.dataIntegratyViolation(
                new DataIntegrityViolationException("Cartão já cadastrado no sistema"),
                new MockHttpServletRequest());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}