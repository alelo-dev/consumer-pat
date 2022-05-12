package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_INVALID_DOCUMENT_NUMBER;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_MISSING_FIELD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class ConsumerValidatorTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private ConsumerValidator validator;

    @Test
    public void testAcceptOk() {

        final Consumer consumer = Consumer.builder().id(1L).name("teste").documentNumber("123456").build();

        validator.accept(consumer);
    }

    @Test
    public void testAcceptErrorBlankNumber() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Consumer consumer = Consumer.builder().id(1L).name("teste").build();
            validator.accept(consumer);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_MISSING_FIELD.getCode()));
    }

    @Test
    public void testAcceptErrorInvalidNumber() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Consumer consumer = Consumer.builder().id(1L).name("teste").documentNumber("error").build();
            validator.accept(consumer);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_INVALID_DOCUMENT_NUMBER.getCode()));
    }

    @Test
    public void testAcceptErrorMissingType() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Consumer consumer = Consumer.builder().id(1L).documentNumber("123456").build();
            validator.accept(consumer);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(CONSUMER_MISSING_FIELD.getCode()));
    }
}