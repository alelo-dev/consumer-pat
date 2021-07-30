package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

import java.util.Collections;
import java.util.Optional;

public final class ConsumerValidation {

    public static void validate(Consumer consumer) {
        shouldNotBeNull(consumer);

        Optional.ofNullable(consumer.getCards())
                .orElse(Collections.emptyList())
                .forEach(CardValidation::validate);
    }

    public static void shouldNotBeNull(Consumer consumer) {
        ObjectUtils.shouldNotBeNull(consumer, Constants.CONSUMER);
    }

    public static void shouldHaveId(Long id) {
        ObjectUtils.shouldNotBeNull(id, Constants.CONSUMER_ID);
    }

}