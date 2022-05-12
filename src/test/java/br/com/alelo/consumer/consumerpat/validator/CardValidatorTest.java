package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.FOOD;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CARD_INVALID_NUMBER;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CARD_MISSING_FIELD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class CardValidatorTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private CardValidator validator;

    @Test
    public void testAcceptOk() {

        final Card card = Card.builder().id(1L).number("123").type(FOOD).balance(100.0).build();

        validator.accept(card);
    }

    @Test
    public void testAcceptErrorBlankNumber() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Card card = Card.builder().id(1L).type(FOOD).balance(100.0).build();
            validator.accept(card);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CARD_MISSING_FIELD.getCode()));
    }

    @Test
    public void testAcceptErrorInvalidNumber() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Card card = Card.builder().id(1L).number("error").type(FOOD).balance(100.0).build();
            validator.accept(card);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CARD_INVALID_NUMBER.getCode()));
    }

    @Test
    public void testAcceptErrorMissingType() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Card card = Card.builder().id(1L).number("123").balance(100.0).build();
            validator.accept(card);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CARD_MISSING_FIELD.getCode()));
    }

}