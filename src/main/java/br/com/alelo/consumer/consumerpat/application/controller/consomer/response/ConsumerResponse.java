package br.com.alelo.consumer.consumerpat.application.controller.consomer.response;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponse {
    private Consumer consumer;

    public static ConsumerResponse of(final Consumer consumer) {
        return new ConsumerResponse(consumer);
    }
}
