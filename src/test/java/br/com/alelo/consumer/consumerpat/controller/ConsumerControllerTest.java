package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllConsumers() {
        // Simula a lista de consumidores que o serviço retornaria
        List<ConsumerDTO> expectedConsumers = new ArrayList<>();
        expectedConsumers.add(new ConsumerDTO("Pessoa1"));
        expectedConsumers.add(new ConsumerDTO("Pessoa2"));

        when(service.getAllConsumersList()).thenReturn(expectedConsumers);

        // Chama o método listAllConsumers
        ResponseEntity<List<ConsumerDTO>> response = consumerController.listAllConsumers();

        // Verifica se o serviço foi chamado corretamente
        verify(service, times(1)).getAllConsumersList();

        // Verifica se a resposta HTTP é OK (200) e se os consumidores retornados são os esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedConsumers, response.getBody());
    }

    @Test
    public void testCreateConsumer() {
        // Simula um ConsumerDTO para ser criado
        ConsumerDTO dtoToCreate = new ConsumerDTO("Pessoa03");

        // Chama o método createConsumer
        consumerController.createConsumer(dtoToCreate);

        // Verifica se o serviço foi chamado corretamente para salvar o consumidor
        verify(service, times(1)).save(dtoToCreate);
    }

    @Test
    public void testUpdateConsumer() {
        // Simula um ConsumerDTO para ser atualizado
        ConsumerDTO dtoToUpdate = new ConsumerDTO("Pessoa04");

        // Chama o método updateConsumer
        consumerController.updateConsumer(dtoToUpdate);

        // Verifica se o serviço foi chamado corretamente para salvar o consumidor (atualização)
        verify(service, times(1)).save(dtoToUpdate);
    }
}
