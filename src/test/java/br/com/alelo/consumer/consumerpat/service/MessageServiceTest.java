package br.com.alelo.consumer.consumerpat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.CONSUMER_MISSING_FIELD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private MessageService service;

    @Test
    public void testGet() {

        Object[] args = {"id"};
        final Locale enUS = new Locale("en", "US");

        final String expected = "The field 'id' can't be empty.";

        when(messageSource.getMessage(eq(CONSUMER_MISSING_FIELD.getMessage()), eq(args), eq(enUS)))
                .thenReturn(expected);

        final String response = service.get(CONSUMER_MISSING_FIELD.getMessage(), "id");

        assertEquals(expected, response);
    }

}