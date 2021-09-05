package br.com.alelo.consumer.pat.repository;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.entity.Card;
import br.com.alelo.consumer.pat.entity.Consumer;
import br.com.alelo.consumer.pat.respository.ConsumerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ConsumerRepositoryTest {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Test
    public void should_create_new_user() {
        Consumer consumer = Consumer.builder()
            .name("Consumer")
            .city("City")
            .build();

        Card card = Card.builder()
            .cardNumber("100100")
            .establishmentType(EstablishmentType.DRUGSTORE)
            .balance(BigDecimal.ZERO)
            .build();

        consumer.setCards(List.of(card));
        consumerRepository.save(consumer);

        Optional<Consumer> optionalConsumer = consumerRepository.findById(1l);

        assertThat(optionalConsumer.get()).isNotNull();
        assertThat(optionalConsumer.get().getCards()).contains(card);
    }

}
