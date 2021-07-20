package br.com.alelo.consumer.consumerpat.validation;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@RequiredArgsConstructor
public class ConsumerValidator {

    public Consumer getConsumerUsingOptional(final Optional<Consumer> optConsumer) {
        if(optConsumer.isEmpty()){
            throw new ResponseStatusException(BAD_REQUEST, "Cliente não encontrado");
        }
        return optConsumer.get();
    }

    public void validateConsumer(final Consumer consumer) {
        if (Objects.isNull(consumer) || Objects.isNull(consumer.getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Cliente não existente");
        }
    }

}
