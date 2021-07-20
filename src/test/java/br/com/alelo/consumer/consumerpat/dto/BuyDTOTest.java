package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyDTOTest {

    @Test
    void Test_getters_and_setters_establishment_type() {
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setEstablishmentType(CardType.FUEL);
        assertEquals(CardType.FUEL, buyDTO.getEstablishmentType());
    }

    @Test
    void Test_getters_and_setters_establishment_name() {
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setEstablishmentName("Test");
        assertEquals("Test", buyDTO.getEstablishmentName());
    }

    @Test
    void Test_getters_and_setters_card_number() {
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setCardNumber(545545L);
        assertEquals(545545L, buyDTO.getCardNumber());
    }

    @Test
    void Test_getters_and_setters_product_description() {
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setProductDescription("Test Description");
        assertEquals("Test Description", buyDTO.getProductDescription());
    }

    @Test
    void Test_getters_and_setters_value(){
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setValue(BigDecimal.valueOf(655.23));
        assertEquals(BigDecimal.valueOf(655.23), buyDTO.getValue());
    }

    @Test
    void Test_getters_and_setters_new_value(){
        BuyDTO buyDTO = BuyDTO.builder().build();
        buyDTO.setNewValue(BigDecimal.valueOf(678.49));
        assertEquals(BigDecimal.valueOf(678.49), buyDTO.getNewValue());
    }

}