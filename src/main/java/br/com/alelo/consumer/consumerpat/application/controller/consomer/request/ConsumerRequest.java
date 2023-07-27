package br.com.alelo.consumer.consumerpat.application.controller.consomer.request;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerRequest {

    @NotNull(message = "Consumer is required.")
    private Consumer consumer;
}
