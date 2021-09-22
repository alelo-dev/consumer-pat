package br.com.alelo.consumer.consumerpat.repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ConsumerRepositoryTest {

    private static final Long CONSUMER_ID = 1L;
    private static final Long FOOD_CARD_NUMBER = 10L;
    private static final Long FUEL_CARD_NUMBER = 20L;
    private static final Long DRUGSTORE_CARD_NUMBER = 30L;

    @Autowired
    private ConsumerRepository consumerRepository;
    private Object Consumer;

    @Test
    public void shouldLoadConsumerById() {
       Optional<Consumer> consumer = consumerRepository.findById(CONSUMER_ID);
       Assert.notNull(consumer, "Usario Encontrado");
    }

    @Test
    public void shouldLoadConsumerByCardNumer() {
        Consumer consumer = consumerRepository.findConsumerByCard(FOOD_CARD_NUMBER);
        Assert.notNull(consumer, "Usario Encontrado");
    }

    @Test
    public void shouldLoadConsumerByCardType() {
        List<Consumer> consumerList = consumerRepository.findConsumerByCardType(FOOD_CARD_NUMBER, FUEL_CARD_NUMBER, DRUGSTORE_CARD_NUMBER);
        Assert.notNull(consumerList, "Usario Encontrado");
    }
}
