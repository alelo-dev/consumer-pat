package br.com.alelo.consumer.consumerpat.service.validator;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static java.util.Locale.getDefault;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Validator for errors of process the consumer
 *
 * @author mcrj
 */
@Component
@RequiredArgsConstructor
public class ConsumerValidator {

    private final MessageSource messageSource;

    /**
     * Validate consumer data to be manipulated
     *
     * @param consumer - {@link Consumer}
     */
    public void validConsumer(final Consumer consumer) {
        if (Objects.isNull(consumer) || Objects.isNull(consumer.getId())) {
            throw new ResponseStatusException(BAD_REQUEST, messageSource.getMessage("msg.consumer.not.exists",
                    null, getDefault()));
        }
    }

    /**
     * Validate found content
     *
     * @param optConsumer - {@link Optional<Consumer>}
     * @return {@link Consumer}
     */
    public Consumer getConsumerByOptional(final Optional<Consumer> optConsumer) {
        if(optConsumer.isEmpty()){
            throw new ResponseStatusException(BAD_REQUEST, messageSource.getMessage("msg.consumer.not.found",
                    null, getDefault()));
        }
        return optConsumer.get();
    }

    public void validateBalanceForUpdate(Consumer consumerPersisted,
                                         Consumer consumerUpdate) {
        final boolean isNotMatch = consumerPersisted.getCards().stream()
                .anyMatch( cardP -> consumerUpdate.getCards().stream()
                        .anyMatch( cardUp -> cardP.getBalance().compareTo(cardUp.getBalance()) != 0));

        if (isNotMatch) {
            throw new ResponseStatusException(FORBIDDEN, messageSource.getMessage("msg.consumer.verify.value.balance",
                    null, getDefault()));
        }
    }
}
