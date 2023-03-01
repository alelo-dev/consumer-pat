package br.com.alelo.consumer.consumerpat.consumer.application.port.out;

import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;

public interface SaveConsumerPort {
    Consumer save(Consumer consumer);
}
