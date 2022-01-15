package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public class ConsumerOutDTO extends ConsumerInDTO {

    public ConsumerOutDTO() {
    }

    public ConsumerOutDTO(Consumer consumer) {
        super(consumer);
    }
}
