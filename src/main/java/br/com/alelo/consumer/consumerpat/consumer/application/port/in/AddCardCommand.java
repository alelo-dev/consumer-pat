package br.com.alelo.consumer.consumerpat.consumer.application.port.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import lombok.Value;

@Value(staticConstructor = "of")
public class AddCardCommand {

    Integer consumerId;
    NewCard newCard;
}
