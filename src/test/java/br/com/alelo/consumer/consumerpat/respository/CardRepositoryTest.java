package br.com.alelo.consumer.consumerpat.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.FOOD;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void testFindByCardNumber() {

        final String number = "123";
        final Card card = Card.builder().number(number).type(FOOD).balance(100.0).build();
        entityManager.persist(card);

        final Optional<Card> response = cardRepository.findByCardNumber(number);

        assertTrue(response.isPresent());
        assertNotNull(response.get().getId());
    }
}