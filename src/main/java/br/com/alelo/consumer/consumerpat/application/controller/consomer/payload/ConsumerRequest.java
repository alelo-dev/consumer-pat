package br.com.alelo.consumer.consumerpat.application.controller.consomer.payload;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerRequest {

    @Valid
    @NotNull(message = "consumer is required.")
    private Consumer consumer;
}
