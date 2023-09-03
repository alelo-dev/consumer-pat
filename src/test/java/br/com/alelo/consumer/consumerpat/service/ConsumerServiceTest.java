package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumerServiceTest {

    @InjectMocks
    private ConsumerService consumerService;

    @Mock
    private ConsumerRepository repository;

    @Mock
    private CardCreditService cardCreditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllConsumersList() {

        // Chama o método getAllConsumersList
        List<ConsumerDTO> consumers = consumerService.getAllConsumersList();

        // Verifica se o repositório foi chamado corretamente
        verify(repository, times(1)).findAll();

    }

    @Test
    public void testSave() {
        // Simula um ConsumerDTO para ser salvo
        ConsumerDTO dtoToSave = new ConsumerDTO();
        dtoToSave.setId(1);
        dtoToSave.setName("Pessoa 1");
        dtoToSave.setFoodCardNumber(123456789);
        dtoToSave.setFuelCardNumber(321654987);

        // Simula um Consumer após a conversão do DTO
        Consumer consumer = new Consumer();

        // Simula um Card após a conversão do DTO
        Card card = new Card();
        card.setId(1);
        card.setCardNumber(123456789);
        card.setCompanyType(CompanyType.FOOD);

        // Configura o comportamento do repositório para salvar o consumidor
        when(repository.save(any(Consumer.class))).thenReturn(consumer);

        // Configura o comportamento do cardCreditService para salvar o cartão
        when(cardCreditService.save(any(Card.class))).thenReturn(card);

        // Chama o método save
        ConsumerDTO savedConsumer = consumerService.save(dtoToSave);

        // Verifica se o consumidor e o cartão foram salvos corretamente
        assertNotNull(savedConsumer);
        assertEquals(dtoToSave.getName(), savedConsumer.getName());
        assertNotNull(savedConsumer.getFoodCardNumber());
        assertEquals(1, savedConsumer.getFuelCardNumber());

    }
}


