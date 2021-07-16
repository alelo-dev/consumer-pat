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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CardIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Value("${tax.food}")
    private BigDecimal foodCashback;

    @Value("${tax.fuel}")
    private BigDecimal fuelTax;

    @Test
    void addBalanceToCard() throws Exception {

        Card card = Card.builder()
                .cardNumber(1111111111111111L)
                .cardBalance(BigDecimal.TEN)
                .typeCard(TypeCard.FOOD)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        mvc.perform(patch("/card/balance/{cardNumber}/{value}", card.getCardNumber(), BigDecimal.TEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();
        assertThat(byCardNumber.getCardBalance().compareTo(new BigDecimal("20.00"))).isZero();
    }

    @Test
    void buyWithFoodCard() throws Exception {

        Card card = Card.builder()
                .cardNumber(2222222222222222L)
                .cardBalance(BigDecimal.TEN)
                .typeCard(TypeCard.FOOD)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.FOOD)
                .productDescription("Product")
                .value(BigDecimal.ONE)
                .build();

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();

        BigDecimal cashback = buyDTO.getValue().multiply(foodCashback);
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue()).add(cashback);
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isZero();

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    void buyWithFuelCard() throws Exception {

        Card card = Card.builder()
                .cardNumber(3333333333333333L)
                .cardBalance(BigDecimal.TEN)
                .typeCard(TypeCard.FUEL)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.FUEL)
                .productDescription("Product")
                .value(BigDecimal.ONE)
                .build();

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();

        BigDecimal tax = buyDTO.getValue().multiply(fuelTax);
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue()).subtract(tax);
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isZero();

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    void buyWithDrugstoreCard() throws Exception {

        Card card = Card.builder()
                .cardNumber(4444444444444444L)
                .cardBalance(BigDecimal.TEN)
                .typeCard(TypeCard.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.DRUGSTORE)
                .productDescription("Product")
                .value(BigDecimal.ONE)
                .build();

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isOk());

        Card byCardNumber = cardRepository.findByCardNumber(card.getCardNumber());
        assertThat(byCardNumber).isNotNull();
        BigDecimal balance = card.getCardBalance().subtract(buyDTO.getValue());
        assertThat(byCardNumber.getCardBalance().compareTo(balance)).isZero();

        testExtractAfterBuy(card.getCardNumber(), buyDTO.getValue());
    }

    @Test
    void cardPurchaseWithNoBalance() throws Exception {

        Card card = Card.builder()
                .cardNumber(5555555555555555L)
                .cardBalance(BigDecimal.ONE)
                .typeCard(TypeCard.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.DRUGSTORE)
                .productDescription("Product")
                .value(BigDecimal.TEN)
                .build();

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BusinessException))
                .andExpect(result -> assertEquals("Insufficient balance for this operation", result.getResolvedException().getMessage()));
    }

    @Test
    void cardPurchaseWithNoExistingCard() throws Exception {

        Consumer consumer = new Consumer();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(9865452198756589L)
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.DRUGSTORE)
                .productDescription("Product")
                .value(BigDecimal.ONE)
                .build();

        mvc.perform(put("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buyDTO)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CardNotFoundException))
                .andExpect(result -> assertEquals(String.format("Card %s Not found!", buyDTO.getCardNumber()), result.getResolvedException().getMessage()));
    }

    @Test
    void cardPurchaseWithDifferentEstablishment() throws Exception {

        Card card = Card.builder()
                .cardNumber(6666666666666666L)
                .cardBalance(BigDecimal.ONE)
                .typeCard(TypeCard.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        BuyDTO buyDTO = BuyDTO.builder()
                .cardNumber(card.getCardNumber())
                .establishmentName("Selling")
                .typeEstablishment(TypeEstablishment.FOOD)
                .productDescription("Product")
                .value(BigDecimal.ONE)
                .build();

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
        assertThat(allByCardNumberBefore.get(0).getPurchaseValue().compareTo(value)).isZero();
    }
}
