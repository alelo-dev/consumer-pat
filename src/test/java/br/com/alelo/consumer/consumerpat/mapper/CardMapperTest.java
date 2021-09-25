package br.com.alelo.consumer.consumerpat.mapper;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.FOOD;
import static br.com.alelo.consumer.consumerpat.enums.ProductType.FUEL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.ProductType;
import org.junit.jupiter.api.Test;

public class CardMapperTest {

    private final CardMapper mapper = new CardMapper();

    @Test
    public void whenMapCardToCardDto_thenSuccess() {
        Long id = 1745L;
        String cardNumber = "5712548";
        ProductType productType = FOOD;
        double balance = 109.75;

        Card card = Card.builder()
                .number(cardNumber)
                .productType(productType)
                .balance(balance)
                .build();
        card.setId(id);

        CardDto dto = mapper.mapModelToDto(card);

        assertEquals(cardNumber, dto.getNumber());
        assertEquals(productType.getId(), dto.getProductId());
    }

    @Test
    public void whenMapCardDtoToCard_thenSuccess() {
        String cardNumber = "5712548";
        ProductType productType = FOOD;

        CardDto dto = CardDto.builder()
                .number(cardNumber)
                .productId(FOOD.getId())
                .build();

        Card card = mapper.mapDtoToModel(dto);

        assertEquals(cardNumber, card.getNumber());
        assertEquals(productType, card.getProductType());
    }

    @Test
    public void whenUpdateCardFromDtoData_thenSuccess() {
        Long id = 1745L;
        String newCardNumber = "5712548";
        ProductType newProductType = FUEL;

        Card card = Card.builder()
                .number("5555555")
                .productType(FOOD)
                .balance(109.75)
                .build();
        card.setId(id);

        CardDto dto = CardDto.builder()
                .number(newCardNumber)
                .productId(FUEL.getId())
                .build();

        card = mapper.updateModelTarget(card, dto);

        assertEquals(id, card.getId());
        assertEquals(newCardNumber, card.getNumber());
        assertEquals(newProductType, card.getProductType());
    }

}
