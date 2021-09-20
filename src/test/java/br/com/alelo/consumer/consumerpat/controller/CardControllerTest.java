package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.utils.MockUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    CardRepository cardRepository;

    @Test
    @Order(0)
    void setBalance() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/consumer")
                .content(MockUtil.getPathString("consumer/consumerCard.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        mockMvc.perform(MockMvcRequestBuilders.patch("/card/balance/1294567990123456?value=50.50")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void setBalanceCardNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/card/balance/129456799012356?value=50.50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("Card number not found")))
                .andReturn();
    }

    @Test
    void buy() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuy.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void buyEstablishmentInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuyEstalishmentTypeInvalid.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("establishmentType invalid")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void buyUnauthorizedTransactionInsufficientFunds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuyInvalidTransaction.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("Unauthorized transaction, insufficient funds")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void buyEstablishmentNameInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuyEstablishmentNameInvalid.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("establishmentName invalid")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void buyValueZero() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuyValueZero.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("value must be bigger than 0")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void buyCardLenghtLessThanSixteen() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/card/buy")
                .content(MockUtil.getPathString("card/cardBuyCardLessThanSixteen.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$..message"
                        , Matchers.hasItem("cardNumber must be 16 characters")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

}
