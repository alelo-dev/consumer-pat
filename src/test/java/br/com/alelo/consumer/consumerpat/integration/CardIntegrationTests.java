package br.com.alelo.consumer.consumerpat.integration;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.enums.TypeEstablishment;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class CardIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Value( "${tax.food}" )
    private BigDecimal foodCashback;

    @Value( "${tax.fuel}" )
    private BigDecimal fuelTax;

    @Test
    public void addBalanceToCard() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(1111111111111111L);
        card.setCardBalance(BigDecimal.TEN);
        card.setTypeCard(TypeCard.FOOD);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        mvc.perform(patch("/card/balance/{cardNumber}/{value}", card.getCardNumber(), BigDecimal.TEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();
        assertThat(byCardNumber.getCardBalance()).isEqualTo(new BigDecimal("20.00"));
    }

    @Test
    public void buyWithFoodCard() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(2222222222222222L);
        card.setCardBalance(BigDecimal.TEN);
        card.setTypeCard(TypeCard.FOOD);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.FOOD);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.ONE);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();

        BigDecimal cashback = buyDTO.getValue().multiply(foodCashback);
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue()).add(cashback);
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isEqualTo(0);

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    public void buyWithFuelCard() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(3333333333333333L);
        card.setCardBalance(BigDecimal.TEN);
        card.setTypeCard(TypeCard.FUEL);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.FUEL);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.ONE);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();

        BigDecimal tax = buyDTO.getValue().multiply(fuelTax);
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue()).subtract(tax);
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isEqualTo(0);

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    public void buyWithDrugstoreCard() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(4444444444444444L);
        card.setCardBalance(BigDecimal.TEN);
        card.setTypeCard(TypeCard.DRUGSTORE);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.DRUGSTORE);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.ONE);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue());
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isEqualTo(0);

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    public void cardPurchaseWithNoBalance() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(5555555555555555L);
        card.setCardBalance(BigDecimal.ONE);
        card.setTypeCard(TypeCard.DRUGSTORE);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.DRUGSTORE);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.TEN);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals("Insufficient balance for this operation", result.getResolvedException().getMessage()));
    }

    @Test
    public void cardPurchaseWithNoExistingCard() throws Exception {

        Consumer consumer = new Consumer();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(9865452198756589L);
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.DRUGSTORE);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.ONE);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CardNotFoundException))
                .andExpect(result -> assertEquals(String.format("Card %s Not found!", buyDTO.getCardNumber()), result.getResolvedException().getMessage()));
    }

    @Test
    public void cardPurchaseWithDifferentEstablishment() throws Exception {

        Consumer consumer = new Consumer();
        Card card = new Card();
        card.setCardNumber(6666666666666666L);
        card.setCardBalance(BigDecimal.ONE);
        card.setTypeCard(TypeCard.DRUGSTORE);
        consumer.setCardList(Collections.singleton(card));

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = new BuyDTO();
        buyDTO.setCardNumber(card.getCardNumber());
        buyDTO.setEstablishmentName("Selling");
        buyDTO.setTypeEstablishment(TypeEstablishment.FOOD);
        buyDTO.setProductDescription("Product");
        buyDTO.setValue(BigDecimal.ONE);

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals("The card cannot be used in this type of establishment.", result.getResolvedException().getMessage()));
    }

    public void testExtractAfterBuy(long cardNumber, BigDecimal value) {

        List<Extract> allByCardNumberBefore = extractRepository.findAllByCardCardNumber(cardNumber);

        assertThat(allByCardNumberBefore.size()).isEqualTo(1);
        assertThat(allByCardNumberBefore.get(0).getCard().getCardNumber()).isEqualTo(cardNumber);
        assertThat(allByCardNumberBefore.get(0).getPurchaseValue().compareTo(value)).isEqualTo(0);
    }
}
