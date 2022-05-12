package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.alelo.consumer.consumerpat.utils.types.CardAndEstablishmentType.DRUGSTORE;
import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.ESTABLISHMENT_MISSING_FIELD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class EstablishmentValidatorTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private EstablishmentValidator validator;

    @Test
    public void testAcceptOk() {

        final Establishment establishment = Establishment.builder().id(1L).name("test").type(DRUGSTORE).build();

        validator.accept(establishment);
    }

    @Test
    public void testAcceptErrorBlankName() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Establishment establishment = Establishment.builder().id(1L).type(DRUGSTORE).build();

            validator.accept(establishment);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(ESTABLISHMENT_MISSING_FIELD.getCode()));
    }

    @Test
    public void testAcceptErrorMissingType() {

        final CustomException exceptionThatWasThrown = assertThrows(CustomException.class, () -> {
            final Establishment establishment = Establishment.builder().id(1L).name("test").build();

            validator.accept(establishment);
        });

        assertThat(exceptionThatWasThrown.getInternalErrorCode(), equalTo(ESTABLISHMENT_MISSING_FIELD.getCode()));
    }
}