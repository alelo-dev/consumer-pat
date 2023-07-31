package br.com.alelo.consumer.consumerpat.application.controller.consomer.payload;

import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Data
@Value(staticConstructor = "of")
public class ConsumerIdResponse {
    UUID id;
}
