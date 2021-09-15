package br.com.alelo.consumer.consumerpat.utils;

import br.com.alelo.consumer.consumerpat.dto.request.ProductRequest;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionUtils {

    public Transaction buildPurchase(Establishment establishment, Card card, ProductRequest productRequest) {
        return Transaction.builder()
                .buyDateTime(LocalDateTime.now())
                .establishment(establishment)
                .card(card)
                .description(productRequest.getDescription())
                .value(productRequest.getValue())
                .build();
    }
}
