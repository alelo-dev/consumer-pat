package br.com.alelo.consumer.consumerpat.application.controller.consomer.response;

import lombok.*;

import java.util.UUID;

@Value(staticConstructor = "of")
@Data
public class ConsumerIdResponse {
    UUID id;
}
