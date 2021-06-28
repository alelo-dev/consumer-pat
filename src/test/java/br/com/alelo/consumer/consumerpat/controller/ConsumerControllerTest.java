package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class ConsumerControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ConsumerRepository consumerRepository;
    @Autowired private ExtractRepository extractRepository;

    private final Consumer testConsumer = makeConsumer("test1");
    private final Consumer testConsumer2 = makeConsumer("test2");
    private final double value = 4.25;
    private final String establishmentName = "Empório dos testes";
    private final String productDescription = "5kg de testes fresquinhos";
    private final Date date = new Date();

    @BeforeEach
    public void cleanUpDB() {
        consumerRepository.deleteAll();
        extractRepository.deleteAll();
    }

    @Test
    void willListAllConsumers() throws Exception {
        consumerRepository.save(testConsumer);
        consumerRepository.save(testConsumer2);
        mvc.perform(get("/consumer/consumerList"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("test1"))
                .andExpect(jsonPath("$[1].name").value("test2"));
    }

    @Test
    void willCreateConsumer() throws Exception {
        mvc.perform(
                        post("/consumer/createConsumer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(testConsumer)))
                .andExpect(status().isOk());
        assertEquals(testConsumer, consumerRepository.findAll().get(0));
    }

    @Test
    void willUpdateConsumer() throws Exception {
        consumerRepository.save(testConsumer);
        final Consumer updatedCustomer = makeConsumer("Updated name");
        updatedCustomer.setId(consumerRepository.findAll().get(0).getId());
        updatedCustomer.setEmail("updated@test.com");
        mvc.perform(
                        post("/consumer/updateConsumer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(updatedCustomer)))
                .andExpect(status().isOk());
        assertEquals(updatedCustomer, consumerRepository.findById(updatedCustomer.getId()).get());
    }

    @Test
    void willFailUpdateIfConsumerNotFound() throws Exception {
        consumerRepository.save(testConsumer);
        final Consumer updatedCustomer = makeConsumer("Updated name");
        updatedCustomer.setId(-1);
        mvc.perform(
                post("/consumer/updateConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomer)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void willFailUpdateWhenTryingToUpdateFoodCardBalance() throws Exception {
        consumerRepository.save(testConsumer);
        final Consumer updatedCustomer = makeConsumer("Updated name");
        updatedCustomer.setId(consumerRepository.findAll().get(0).getId());
        updatedCustomer.setFoodCardBalance(10000);
        mvc.perform(
                post("/consumer/updateConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomer)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void willFailUpdateWhenTryingToUpdateDrugstoreCardBalance() throws Exception {
        consumerRepository.save(testConsumer);
        final Consumer updatedCustomer = makeConsumer("Updated name");
        updatedCustomer.setId(consumerRepository.findAll().get(0).getId());
        updatedCustomer.setDrugstoreCardBalance(10000);
        mvc.perform(
                post("/consumer/updateConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomer)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void willFailUpdateWhenTryingToUpdateFuelCardBalance() throws Exception {
        consumerRepository.save(testConsumer);
        final Consumer updatedCustomer = makeConsumer("Updated name");
        updatedCustomer.setId(consumerRepository.findAll().get(0).getId());
        updatedCustomer.setFuelCardBalance(10000);
        mvc.perform(
                post("/consumer/updateConsumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomer)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void willSetBalanceForDrugstoreCard() throws Exception {
        consumerRepository.save(testConsumer);
        mvc.perform(buildSetBalanceRequest(testConsumer.getDrugstoreNumber()))
                .andExpect(status().isOk());
        assertThat(
                consumerRepository
                        .findByDrugstoreNumber(testConsumer.getDrugstoreNumber())
                        .getDrugstoreCardBalance(),
                is(testConsumer.getDrugstoreCardBalance() + value));
    }

    @Test
    void willSetBalanceForFoodCard() throws Exception {
        consumerRepository.save(testConsumer);
        mvc.perform(buildSetBalanceRequest(testConsumer.getFoodCardNumber()))
                .andExpect(status().isOk());
        assertThat(
                consumerRepository
                        .findByFoodCardNumber(testConsumer.getFoodCardNumber())
                        .getFoodCardBalance(),
                is(testConsumer.getFoodCardBalance() + value));
    }

    @Test
    void willSetBalanceForFuelCard() throws Exception {
        consumerRepository.save(testConsumer);
        mvc.perform(buildSetBalanceRequest(testConsumer.getFuelCardNumber()))
                .andExpect(status().isOk());
        assertThat(
                consumerRepository
                        .findByFuelCardNumber(testConsumer.getFuelCardNumber())
                        .getFuelCardBalance(),
                is(testConsumer.getFuelCardBalance() + value));
    }

    @Test
    void willBuyFoodAndGenerateExtract() throws Exception {
        double expectedValue = value - (value / 100) * 10;

        consumerRepository.save(testConsumer);
        mvc.perform(buildBuyRequest(1, testConsumer.getFoodCardNumber()))
                .andExpect(status().isOk());

        assertThat(
                consumerRepository
                        .findByFoodCardNumber(testConsumer.getFoodCardNumber())
                        .getFoodCardBalance(),
                is(testConsumer.getFoodCardBalance() - expectedValue));

        Extract expectedExtract =
                new Extract(
                        establishmentName,
                        productDescription,
                        new Date(),
                        testConsumer.getFoodCardNumber(),
                        expectedValue);
        assertThat(extractRepository.findAll().get(0), is(expectedExtract));
    }

    @Test
    void willBuyDrugsAndGenerateExtract() throws Exception {
        consumerRepository.save(testConsumer);
        mvc.perform(buildBuyRequest(2, testConsumer.getDrugstoreNumber()))
                .andExpect(status().isOk());

        assertThat(
                consumerRepository
                        .findByDrugstoreNumber(testConsumer.getDrugstoreNumber())
                        .getDrugstoreCardBalance(),
                is(testConsumer.getDrugstoreCardBalance() - value));

        Extract expectedExtract =
                new Extract(
                        establishmentName,
                        productDescription,
                        new Date(),
                        testConsumer.getDrugstoreNumber(),
                        value);
        assertThat(extractRepository.findAll().get(0), is(expectedExtract));
    }

    @Test
    void willBuyFuelAndGenerateExtract() throws Exception {
        double expectedValue = value + (value / 100) * 35;

        consumerRepository.save(testConsumer);
        mvc.perform(buildBuyRequest(3, testConsumer.getFuelCardNumber()))
                .andExpect(status().isOk());

        assertThat(
                consumerRepository
                        .findByFuelCardNumber(testConsumer.getFuelCardNumber())
                        .getFuelCardBalance(),
                is(testConsumer.getFuelCardBalance() - expectedValue));

        Extract expectedExtract =
                new Extract(
                        establishmentName,
                        productDescription,
                        new Date(),
                        testConsumer.getFuelCardNumber(),
                        expectedValue);
        assertThat(extractRepository.findAll().get(0), is(expectedExtract));
    }

    private MockHttpServletRequestBuilder buildSetBalanceRequest(int cardNumber) {
        return get("/consumer/setcardbalance")
                .param("cardNumber", String.valueOf(cardNumber))
                .param("value", String.valueOf(value));
    }

    private MockHttpServletRequestBuilder buildBuyRequest(int establishmentType, int cardNumber) {
        return get("/consumer/buy")
                .param("establishmentType", String.valueOf(establishmentType))
                .param("establishmentName", establishmentName)
                .param("cardNumber", String.valueOf(cardNumber))
                .param("productDescription", productDescription)
                .param("value", String.valueOf(value));
    }

    private Consumer makeConsumer(String name) {
        return new Consumer(
                0,
                name,
                1234,
                date,
                12345,
                23456,
                34567,
                "email@test.com",
                "Test Street",
                123,
                "City",
                "Country",
                45678,
                56789,
                10.11,
                67890,
                11.23,
                78901,
                12.34);
    }

    private String toJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
