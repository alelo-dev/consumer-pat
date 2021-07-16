package br.com.alelo.consumer.consumerpat.integration;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import br.com.alelo.consumer.consumerpat.exception.CardBalanceChangeNotAllowedException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class ConsumerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Test
    void createConsumer() throws Exception {

        addConsumer(1);

        long consumerCount = consumerRepository.count();
        assertThat(consumerCount > 0).isTrue();
    }

    @Test
    void updateConsumer() throws Exception {

        int documentNumber = 9865;
        Consumer consumer = Consumer.builder()
                .name("Fulano")
                .documentNumber(documentNumber)
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        Consumer consumerOriginal = consumerRepository.findByDocumentNumber(documentNumber);
        assertThat(consumerOriginal).isNotNull();
        assertThat(consumerOriginal.getDocumentNumber()).isEqualTo(consumer.getDocumentNumber());
        assertThat(consumerOriginal.getName()).isEqualTo(consumer.getName());

        consumerOriginal.setName("Ciclano");
        consumerOriginal.setContactList(new HashSet<>());
        consumerOriginal.setCardList(new HashSet<>());

        mvc.perform(put("/consumer/{consumerId}", consumerOriginal.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumerOriginal)))
                .andExpect(status().isOk());

        Consumer consumerEdited = consumerRepository.findByDocumentNumber(documentNumber);
        assertThat(consumerEdited).isNotNull();
        assertThat(consumerEdited.getName()).isEqualTo(consumerOriginal.getName());
        assertThat(consumerEdited.getDocumentNumber()).isEqualTo(consumer.getDocumentNumber());
    }

    @Test
    void deleteConsumer() throws Exception {

        Consumer consumer = Consumer.builder()
                .documentNumber(563)
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        Consumer consumerOriginal = consumerRepository.findByDocumentNumber(consumer.getDocumentNumber());
        assertThat(consumerOriginal).isNotNull();

        mvc.perform(delete("/consumer/{consumerId}", consumerOriginal.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Consumer consumerDeleted = consumerRepository.findByDocumentNumber(consumer.getDocumentNumber());
        assertThat(consumerDeleted).isNull();
    }

    @Test
    void listPageableConsumer() throws Exception {

        int quantityInsert = 200;
        long initialCount = consumerRepository.count();

        addConsumer(quantityInsert);

        long consumerCount = consumerRepository.count();
        assertThat(consumerCount - initialCount).isEqualTo(quantityInsert);

        mvc.perform(get("/consumer?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(10)));
    }

    private void addConsumer(int quantity) throws Exception {

        for (int i = 0; i < quantity; i++) {
            Consumer consumer = Consumer.builder()
                    .documentNumber(i)
                    .build();

            mvc.perform(post("/consumer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(consumer)))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void updateConsumerChangingCardBalance() throws Exception {

        int documentNumber = 86500;

        Card card = Card.builder()
                .cardNumber(8650865086508650L)
                .cardBalance(BigDecimal.ONE)
                .typeCard(TypeCard.DRUGSTORE)
                .build();

        Consumer consumer = Consumer.builder()
                .documentNumber(documentNumber)
                .cardList(Collections.singleton(card))
                .build();

        mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumer)))
                .andExpect(status().isOk());

        Consumer consumerOriginal = consumerRepository.findByDocumentNumber(documentNumber);
        consumerOriginal.setName("Ciclano");
        consumerOriginal.setContactList(new HashSet<>());
        consumerOriginal.getCardList().stream().findFirst().get().setCardBalance(BigDecimal.TEN);

        mvc.perform(put("/consumer/{consumerId}", consumerOriginal.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumerOriginal)))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CardBalanceChangeNotAllowedException))
                .andExpect(result -> assertEquals(String.format("Not is allowed change the balance of the card %s", card.getCardNumber()), result.getResolvedException().getMessage()));
    }
}
