package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements Mapper<Card, CardDto> {

    @Override
    public Card mapDtoToModel(final CardDto dto) {
        return Card.builder()
                .number(dto.getNumber())
                .productType(parseProductIdToProductType(dto.getProductId()))
                .build();
    }

    @Override
    public CardDto mapModelToDto(final Card model) {
        return CardDto.builder()
                .number(model.getNumber())
                .productId(model.getProductType().getId())
                .build();
    }

    @Override
    public Card updateModelTarget(final Card target, final CardDto origin) {
        target.setNumber(origin.getNumber());
        target.setProductType(parseProductIdToProductType(origin.getProductId()));

        return target;
    }

    private ProductType parseProductIdToProductType(final int productId) {
        return ProductType.of(productId).orElseThrow(() -> new IllegalArgumentException("ProductId invalid"));
    }

}
