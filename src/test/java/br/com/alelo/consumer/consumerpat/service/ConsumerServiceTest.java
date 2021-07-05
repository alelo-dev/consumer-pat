package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BussinessException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @Autowired
    ConsumerService consumerService;

    @MockBean
    private ConsumerRepository repository;
    private Random random;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        random = new Random();
    }

    @Test
    public void buscaTodosComSucesso() {

        List<Consumer> consumerList = geConsumerList();
        Page<Consumer> page = new PageImpl<>(consumerList);
        when(repository.findAll(PageRequest.of(1, 25))).thenReturn(page);
        Page<Consumer> consumers = consumerService.findAll(PageRequest.of(1, 25));

        assertEquals(consumerList.size(), consumers.getTotalElements());

    }

    @Test
    public void salvarComSucesso() {
        Consumer consumer = getConsumer();
        consumer.setId(null);
        when(repository.save(consumer)).thenReturn(getConsumer());
        Consumer save = consumerService.save(consumer);

        Assertions.assertNotNull(save.getId());
    }

    @Test
    public void atualizarComSucesso() throws BussinessException {
        Consumer saved = getConsumer();
        saved.setName("Alterado");

        when(repository.findById(saved.getId())).thenReturn(Optional.of(saved));
        when(repository.save(saved)).thenReturn(saved);

        Consumer update = consumerService.update(saved);

        assertEquals(saved.getName(), update.getName());
    }

    @Test
    public void atualizarComSaldoAlterado() throws BussinessException {

        Consumer saved = getConsumer(getCards());

        Card foodCard = getCard(CardType.FOOD);
        Card fuelCard = getCard(CardType.FUEL);
        foodCard.setBalance(1000.0);
        Consumer changed = getConsumer(foodCard,fuelCard);
        changed.setId(saved.getId());

        when(repository.findById(changed.getId())).thenReturn(Optional.of(saved));

        assertThrows(BussinessException.class, () -> consumerService.update(changed));
    }

    @Test
    public void atualizarConsumerInexistente() {
        Consumer consumer = getConsumer();
        consumer.setName("Alterado");

        when(repository.findById(consumer.getId())).thenReturn(Optional.empty());

        assertThrows(BussinessException.class, () -> consumerService.update(consumer));
    }

    private List<Consumer> geConsumerList() {
        return Arrays.asList(getConsumer(), getConsumer());
    }

    private Consumer getConsumer() {

        long id = random.nextLong();

        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setName("Teste " + id);
        consumer.setDocumentNumber(random.nextInt(999999));
        consumer.setBirthDate(new Date());

        return consumer;
    }

    private Consumer getConsumer(Card... cardList) {
        Consumer consumer = getConsumer();
        consumer.setCardList(new HashSet<>(Arrays.asList(cardList)));
        return consumer;
    }

    private Card[] getCards() {
        return Arrays.asList(getCard(CardType.FOOD), getCard(CardType.FUEL)).toArray(Card[]::new);
    }

    private Card getCard(CardType cardType) {
        Card card = new Card();
        card.setType(cardType);
        card.setBalance(500.0);
        card.setNumber(random.nextLong());
        card.setId(random.nextLong());
        return card;
    }
}
