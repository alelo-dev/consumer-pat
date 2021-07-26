package br.com.alelo.consumer.consumerpat.facade.converter;

import br.com.alelo.consumer.consumerpat.controller.dto.BueDto;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Transaction;
import br.com.alelo.consumer.consumerpat.model.type.EstablishmentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConverter {

    public static Transaction toEntity(final BueDto bue) {
        return Transaction.builder().establishmentName(bue.getEstablishmentName())
                .establishmentType(EstablishmentType.values()[bue.getEstablishmentType() - 1])
                .productDescription(bue.getProductDescription()).value(bue.getValue())
                .card(Card.builder().number(bue.getCardNumber()).build())
                .consumer(Consumer.builder().id(bue.getConsumerId()).build()).build();
    }

}
