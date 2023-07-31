package br.com.alelo.consumer.consumerpat.domain.card.entity;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    private Validator validator;

    public CardTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCardCreationValid() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;

        Card card = new Card(cardNumber, cardType);
    }

    @Test
    void testAddCardBalance() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.DRUGSTORE;
        Card card = new Card(cardNumber, cardType);

        CardBalance cardBalance = new CardBalance(card.getCardNumber());

        card.addCardBalance(cardBalance);
    }

    @Test
    void testValidCard() {
        CardNumber cardNumber = new CardNumber("1234567812345678");
        CardType cardType = CardType.FOOD;
        Card card = new Card(cardNumber, cardType);

        assertThat(card.getCardNumber()).isEqualTo(cardNumber);
        assertThat(card.getCardType()).isEqualTo(cardType);
        assertThat(card.getCardBalance()).isNull();

        Set<ConstraintViolation<Card>> violations = validator.validate(card);
        assertThat(violations).isEmpty();
    }

    @Test
    void testInvalidCard() {
        Card card = new Card(null, null);

        Set<ConstraintViolation<Card>> violations = validator.validate(card);

        assertThat(violations).hasSize(2);
        assertThat(violations).extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "cardNumber is required",
                        "cardType is required"
                );
    }
}