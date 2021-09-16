package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.data.entity.Consumer;
import br.com.alelo.consumer.consumerpat.tool.ConsumerTool;
import br.com.alelo.consumer.consumerpat.tool.HttpResponse;
import br.com.alelo.consumer.consumerpat.tool.HttpTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.text.ParseException;

import static br.com.alelo.consumer.consumerpat.tool.ConsumerTool.CONSUMER_NAME;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerControllerTests {

    @LocalServerPort
    private int port;

    HttpTool http() {
        String baseUrl = "http://localhost:" + port;
        return new HttpTool(baseUrl);
    }

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void createConsumerTest() throws IOException, ParseException {
        Consumer consumer = ConsumerTool.buildFakeConsumer();
        String reqBody = mapper.writeValueAsString(consumer);
        System.out.println("[ConsumerControllerTests][createConsumerTest] req body: " + reqBody);
        HttpResponse res = http().post("consumer", reqBody);
        System.out.println("[ConsumerControllerTests][createConsumerTest] res code: " + res.getCode());
        System.out.println("[ConsumerControllerTests][createConsumerTest] res body: " + res.getBody());
        Consumer created = mapper.readValue(res.getBody(), Consumer.class);
        System.out.println("[ConsumerControllerTests][createConsumerTest] created: " + created);
        Assertions.assertThat(created.getName()).isEqualTo(CONSUMER_NAME);
    }

    @Test
    void listAllConsumersTest() throws IOException {
        HttpResponse res = http().get("consumer");
        System.out.println("[ConsumerControllerTests][listAllConsumersTest] res code: " + res.getCode());
        System.out.println("[ConsumerControllerTests][listAllConsumersTest] res body: " + res.getBody());
        Assertions.assertThat(res.getBody()).contains("content");
    }

    private Consumer createConsumer() throws ParseException, IOException {
        Consumer consumer = ConsumerTool.buildFakeConsumer();
        String createReqBody = mapper.writeValueAsString(consumer);
        HttpResponse createRes = http().post("consumer", createReqBody);
        return mapper.readValue(createRes.getBody(), Consumer.class);
    }

    @Test
    void updateConsumerTest() throws IOException, ParseException {
        Consumer created = createConsumer();
        System.out.println("[ConsumerControllerTests][updateConsumerTest] created: " + created);
        String newConsumerName = "Michael Jackson";
        String updateReqBody = mapper.writeValueAsString(created.setName(newConsumerName));
        HttpResponse updateRes = http().put("consumer", updateReqBody);
        System.out.println("[ConsumerControllerTests][updateConsumerTest] res code: " + updateRes.getCode());
        System.out.println("[ConsumerControllerTests][updateConsumerTest] res body: " + updateRes.getBody());
        Consumer updated = mapper.readValue(updateRes.getBody(), Consumer.class);
        System.out.println("[ConsumerControllerTests][updateConsumerTest] updated: " + updated);
        Assertions.assertThat(updated.getName()).isNotEqualTo(CONSUMER_NAME);
        Assertions.assertThat(updated.getName()).isEqualTo(newConsumerName);
    }

    @Test
    void updateConsumerWithoutIdTest() throws IOException, ParseException {
        Consumer created = createConsumer();
        String updateReqBody = mapper.writeValueAsString(created.setId(null));
        HttpResponse updateRes = http().put("consumer", updateReqBody);
        System.out.println("[ConsumerControllerTests][updateConsumerWithoutIdTest] res code: " + updateRes.getCode());
        System.out.println("[ConsumerControllerTests][updateConsumerWithoutIdTest] res body: " + updateRes.getBody());
        Assertions.assertThat(updateRes.getCode()).isEqualTo(400);
        Assertions.assertThat(updateRes.getBody()).contains("invalid id");
    }

    @Test
    void findConsumerCardsTest() throws IOException, ParseException {
        Consumer consumer = createConsumer();
        HttpResponse res = http().get(String.format("consumer/%s/card", consumer.getId()));
        System.out.println("[ConsumerControllerTests][findConsumerCardsTest] res code: " + res.getCode());
        System.out.println("[ConsumerControllerTests][findConsumerCardsTest] res body: " + res.getBody());
        Assertions.assertThat(res.getBody()).contains("balance");
    }

    @Test
    void findConsumerTest() throws IOException, ParseException {
        Consumer consumer = createConsumer();
        HttpResponse res = http().get(String.format("consumer/%s", consumer.getId()));
        System.out.println("[ConsumerControllerTests][findConsumerTest] res code: " + res.getCode());
        System.out.println("[ConsumerControllerTests][findConsumerTest] res body: " + res.getBody());
        Assertions.assertThat(res.getBody()).contains(CONSUMER_NAME);
    }

    @Test
    void findUnknownConsumerTest() throws IOException {
        HttpResponse res = http().get(String.format("consumer/%s", "9999999"));
        System.out.println("[ConsumerControllerTests][findUnknownConsumerTest] res code: " + res.getCode());
        System.out.println("[ConsumerControllerTests][findUnknownConsumerTest] res body: " + res.getBody());
        Assertions.assertThat(res.getCode()).isEqualTo(400);
        Assertions.assertThat(res.getBody()).contains("unknown consumer");
    }
}
