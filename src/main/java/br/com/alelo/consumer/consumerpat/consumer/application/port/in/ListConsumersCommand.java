package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import lombok.Value;
import org.springframework.data.domain.Pageable;

@Value(staticConstructor = "of")
public class ListConsumersCommand {

    Pageable pageable;
}
