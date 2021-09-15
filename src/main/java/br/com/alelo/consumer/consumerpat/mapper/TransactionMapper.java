package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.request.ProductRequest;
import br.com.alelo.consumer.consumerpat.dto.response.TransactionDto;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CardMapper cardMapper;
    private final EstablishmentMapper establishmentMapper;

    public TransactionDto toDto(Transaction purchase) {
        return TransactionDto.builder()
                .id(purchase.getId())
                .buyDateTime(purchase.getBuyDateTime())
                .card(cardMapper.toDto(purchase.getCard()))
                .establishment(establishmentMapper.toDto(purchase.getEstablishment()))
                .product(ProductRequest.builder()
                                        .description(purchase.getDescription())
                                        .value(purchase.getValue())
                                        .build())
                .build();
    }
}
