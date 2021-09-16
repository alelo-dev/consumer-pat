package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.data.entity.Card;
import br.com.alelo.consumer.consumerpat.data.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.Industry;
import br.com.alelo.consumer.consumerpat.domain.Purchase;
import br.com.alelo.consumer.consumerpat.tool.ConsumerTool;
import br.com.alelo.consumer.consumerpat.tool.HttpResponse;
import br.com.alelo.consumer.consumerpat.tool.HttpTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardControllerTests {

    @LocalServerPort
    private int port;

    HttpTool http() {
        String baseUrl = "http://localhost:" + port;
        return new HttpTool(baseUrl);
    }

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void findCardTest() throws IOException, ParseException {
        Card card = createCard(Industry.FOOD);
        System.out.println("[CardControllerTests][findCardTest] card from consumer's cards: " + card);
        HttpResponse cardRes = http().get(format("card/%s", card.getNumber()));
        System.out.println("[CardControllerTests][findCardTest] card directly found: " + cardRes.getBody());
        Assertions.assertThat(cardRes.getBody()).contains(card.getNumber());
    }

    @Test
    void findUnknownCardTest() throws IOException, ParseException {
        HttpResponse cardRes = http().get(format("card/%s", "UNKNOWN_CARD_NUMBER"));
        System.out.println("[CardControllerTests][findUnknownCardTest] card directly found: " + cardRes.getBody());
        Assertions.assertThat(cardRes.getCode()).isEqualTo(400);
        Assertions.assertThat(cardRes.getBody()).contains("unknown card");
    }

    @Test
    void addBalanceTest() throws IOException, ParseException {
        Card card = createCard(Industry.DRUGSTORE);
        System.out.println("[CardControllerTests][addBalanceTest] card before: " + card);
        http().post(format("card/%s/balance", card.getNumber()), "{\"value\": 100 }");
        HttpResponse cardRes = http().get(format("card/%s", card.getNumber()));
        System.out.println("[CardControllerTests][addBalanceTest] card after: " + cardRes.getBody());
        Assertions.assertThat(cardRes.getBody()).contains("\"balance\":100.00");
    }

    @Test
    void buyFoodTest() throws IOException, ParseException {
        Card card = createCard(Industry.FOOD);
        addBalanceTo(card.getNumber(), new BigDecimal("100.00"));
        Purchase purchase = new Purchase()
                .setCardNumber(card.getNumber())
                .setEstablishmentName("Awesome Establishment")
                .setEstablishmentType(card.getType())
                .setProductDescription("Awesome Product")
                .setValue(new BigDecimal("10.00"));
        HttpResponse buyRes = http().post("card/buy", mapper.writeValueAsString(purchase));
        System.out.println("[CardControllerTests][buyTest] res body: " + buyRes.getBody());
        Assertions.assertThat(buyRes.getBody()).contains("\"balance\":91.00");
    }

    @Test
    void buyFuelTest() throws IOException, ParseException {
        Card card = createCard(Industry.FUEL);
        addBalanceTo(card.getNumber(), new BigDecimal("100.00"));
        Purchase purchase = new Purchase()
                .setCardNumber(card.getNumber())
                .setEstablishmentName("Crazy Establishment")
                .setEstablishmentType(card.getType())
                .setProductDescription("10L Gas")
                .setValue(new BigDecimal("10.00"));
        HttpResponse buyRes = http().post("card/buy", mapper.writeValueAsString(purchase));
        System.out.println("[CardControllerTests][buyTest] res body: " + buyRes.getBody());
        Assertions.assertThat(buyRes.getBody()).contains("\"balance\":86.50");
    }

    @Test
    void buyWithoutFundsTest() throws IOException, ParseException {
        Card card = createCard(Industry.FOOD);
        Purchase purchase = new Purchase()
                .setCardNumber(card.getNumber())
                .setEstablishmentName("Awesome Establishment")
                .setEstablishmentType(card.getType())
                .setProductDescription("Awesome Product")
                .setValue(new BigDecimal("10.00"));
        HttpResponse buyRes = http().post("card/buy", mapper.writeValueAsString(purchase));
        System.out.println("[CardControllerTests][buyWithoutFundsTest] res code: " + buyRes.getCode());
        System.out.println("[CardControllerTests][buyWithoutFundsTest] res body: " + buyRes.getBody());
        Assertions.assertThat(buyRes.getCode()).isEqualTo(400);
        Assertions.assertThat(buyRes.getBody()).contains("insufficient funds");
    }

    @Test
    void buyWithMismatchIndustryCardTest() throws IOException, ParseException {
        Card card = createCard(Industry.DRUGSTORE);
        Purchase purchase = new Purchase()
                .setCardNumber(card.getNumber())
                .setEstablishmentName("Awesome Establishment")
                .setEstablishmentType(Industry.FOOD)
                .setProductDescription("Awesome Product")
                .setValue(new BigDecimal("10.00"));
        HttpResponse buyRes = http().post("card/buy", mapper.writeValueAsString(purchase));
        System.out.println("[CardControllerTests][buyWithMismatchIndustryCardTest] res code: " + buyRes.getCode());
        System.out.println("[CardControllerTests][buyWithMismatchIndustryCardTest] res body: " + buyRes.getBody());
        Assertions.assertThat(buyRes.getCode()).isEqualTo(400);
        Assertions.assertThat(buyRes.getBody()).contains("industry mismatch");
    }

    @Test
    void findCardExtractTest() throws IOException, ParseException {
        String productName = "Awesome Product";
        Card card = createCardAddBalanceAndBuy(productName);
        HttpResponse extractRes = http().get(format("card/%s/extract", card.getNumber()));
        System.out.println("[CardControllerTests][findCardExtractTest] res body: " + extractRes.getBody());
        Assertions.assertThat(extractRes.getBody()).contains(productName);
        Assertions.assertThat(extractRes.getBody()).contains("dateBuy");
    }

    private Consumer createConsumer() throws ParseException, IOException {
        Consumer consumer = ConsumerTool.buildFakeConsumer();
        String createReqBody = mapper.writeValueAsString(consumer);
        HttpResponse createRes = http().post("consumer", createReqBody);
        return mapper.readValue(createRes.getBody(), Consumer.class);
    }

    private Card createCard(Industry type) throws IOException, ParseException {
        Consumer consumer = createConsumer();
        HttpResponse cardsRes = http().get(format("consumer/%s/card", consumer.getId()));
        List<Card> cards = Arrays.asList(mapper.readValue(cardsRes.getBody(), Card[].class));
        Optional<Card> firstCard = cards.stream().filter(it -> it.getType().equals(type)).findFirst();
        Assertions.assertThat(firstCard.isPresent()).isTrue();
        return firstCard.get();
    }

    private void addBalanceTo(String cardNumber, BigDecimal value) throws IOException {
        http().post(format("card/%s/balance", cardNumber), "{\"value\": " + value.toString() + " }");
    }

    Card createCardAddBalanceAndBuy(String productDescription) throws IOException, ParseException {
        Card card = createCard(Industry.FUEL);
        addBalanceTo(card.getNumber(), new BigDecimal("100.00"));
        Purchase purchase = new Purchase()
                .setCardNumber(card.getNumber())
                .setEstablishmentName("Awesome Establishment")
                .setEstablishmentType(card.getType())
                .setProductDescription(productDescription)
                .setValue(new BigDecimal("10.00"));
        http().post("card/buy", mapper.writeValueAsString(purchase));
        return card;
    }
}
