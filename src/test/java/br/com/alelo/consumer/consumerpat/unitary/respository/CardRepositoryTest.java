package br.com.alelo.consumer.consumerpat.unitary.respository;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class CardRepositoryTest {

    @Autowired
    private CardRepository repository;

    @Test
    public void testSaveCard() {
        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(1234123412341234L)
                .cardBalance(BigDecimal.TEN)
                .build();

        repository.save(card);

        assertNotNull(card.getId());
    }

    @Test
    public void testFindByCardNumber() {
        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(4321432143214321L)
                .cardBalance(BigDecimal.TEN)
                .build();

        repository.save(card);

        Card byCardNumber = repository.findByCardNumber(card.getCardNumber());

        assertNotNull(byCardNumber);
    }

    @Test
    public void testDeleteCard() {
        Card card = Card.builder()
                .typeCard(TypeCard.FOOD)
                .cardNumber(1111111111111111L)
                .cardBalance(BigDecimal.TEN)
                .build();

        repository.save(card);
        repository.delete(card);

        Card byCardNumber = repository.findByCardNumber(card.getCardNumber());

        assertNull(byCardNumber);
    }
}