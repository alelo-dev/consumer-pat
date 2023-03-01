package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class UpdateConsumerCommand {

    Integer consumerId;
    UpdateConsumer updateConsumer;
}
