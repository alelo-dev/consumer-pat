package br.com.alelo.consumer.consumerpat.validator;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static br.com.alelo.consumer.consumerpat.utils.types.ExceptionsType.ESTABLISHMENT_MISSING_FIELD;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class EstablishmentValidator implements Consumer<Establishment> {

    @Autowired
    MessageService messageService;

    @Override
    public void accept(Establishment establishment) {

        if (isBlank(establishment.getName())) {
            throw new CustomException(messageService.get(ESTABLISHMENT_MISSING_FIELD.getMessage(), "name"),
                    HttpStatus.BAD_REQUEST, ESTABLISHMENT_MISSING_FIELD.getCode());
        }

        if (isNull(establishment.getType())) {
            throw new CustomException(messageService.get(ESTABLISHMENT_MISSING_FIELD.getMessage(), "type"),
                    HttpStatus.BAD_REQUEST, ESTABLISHMENT_MISSING_FIELD.getCode());

        }
    }
}