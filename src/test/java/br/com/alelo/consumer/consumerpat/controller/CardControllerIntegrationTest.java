package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.BaseIntegrationTest;
import br.com.alelo.consumer.consumerpat.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.builder.CardBuilder.*;
import static br.com.alelo.consumer.consumerpat.builder.ConsumerBuilder.emptyConsumer;
import static br.com.alelo.consumer.consumerpat.builder.IncreaseCardBalanceBuilder.fullIncreaseCardBalanceDTO;
import static br.com.alelo.consumer.consumerpat.builder.TransactionBuilder.fullTransactionDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private ExtractRepository extractRepository;

    private Card fuelCard;
    private Card foodCard;
    private Card drugstoreCard;

    @BeforeEach
    public void resetRepository() {
        cleanRepository();
        final Consumer consumerDB = consumerRepository.save(getConsumer());

        fuelCard = fuelCard();
        fuelCard.setConsumer(consumerDB);
        fuelCard = cardRepository.save(fuelCard);

        foodCard = foodCard();
        foodCard.setConsumer(consumerDB);
        foodCard = cardRepository.save(foodCard);

        drugstoreCard = drugstoreCard();
        drugstoreCard.setConsumer(consumerDB);
        drugstoreCard = cardRepository.save(drugstoreCard);
    }

    @AfterEach
    public void cleanRepository() {
        extractRepository.deleteAll();
        cardRepository.deleteAll();
        consumerRepository.deleteAll();
    }

    @Test
    public void shouldIncreaseCardBalance() throws Exception {
        IncreaseCardBalanceDTO request = fullIncreaseCardBalanceDTO();
        request.setNumber(fuelCard.getNumber());

        mvc.perform(post("/card/increaseCardBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        Card card = getCard(fuelCard.getId());
        assertEquals(new BigDecimal("11"), card.getBalance());
    }

    @Test
    public void shouldProcessFoodTransaction() throws Exception {
        TransactionDTO request = fullTransactionDTO();
        request.setCardNumber(foodCard.getNumber());
        request.setEstablishmentType(CardType.FOOD_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        Card card = getCard(foodCard.getId());
        assertEquals(new BigDecimal("0.10"), card.getBalance());
        assertExtract(request, new BigDecimal("0.90"));
    }

    @Test
    public void shouldProcessFuelTransaction() throws Exception {
        TransactionDTO request = fullTransactionDTO();
        request.setCardNumber(fuelCard.getNumber());
        request.setEstablishmentType(CardType.FUEL_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        Card card = getCard(fuelCard.getId());
        assertEquals(new BigDecimal("8.65"), card.getBalance());
        assertExtract(request, new BigDecimal("1.35"));
    }

    @Test
    public void shouldProcessDrugstoreTransaction() throws Exception {
        TransactionDTO request = fullTransactionDTO();
        request.setCardNumber(drugstoreCard.getNumber());
        request.setEstablishmentType(CardType.DRUGSTORE_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        Card card = getCard(drugstoreCard.getId());
        assertEquals(new BigDecimal("9.00"), card.getBalance());
        assertExtract(request, new BigDecimal("1.00"));
    }

    private Card getCard(Long id) {
        assertNotNull(id);
        final Optional<Card> optional = cardRepository.findById(id);
        assertTrue(optional.isPresent());
        return optional.get();
    }

    private void assertExtract(TransactionDTO request, BigDecimal value) {
        final List<Extract> extracts = extractRepository.findAll();
        assertEquals(1, extracts.size());
        final Extract extract = extracts.get(0);
        assertEquals(request.getCardNumber(), extract.getCard().getNumber());
        assertEquals(request.getEstablishmentName(), extract.getEstablishmentName());
        assertEquals(request.getProductDescription(), extract.getProductDescription());
        assertEquals(value, extract.getValue());
    }

    private Consumer getConsumer() {
        return consumerRepository.save(emptyConsumer());
    }
}