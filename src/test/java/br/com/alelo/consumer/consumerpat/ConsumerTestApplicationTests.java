package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.controller.CardController;
import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ConsumerTestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConsumerController consumerController;

    @Autowired
    private CardController cardController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(consumerController);
        Assertions.assertNotNull(cardController);
    }

    @Test
    void createConsumer200() throws Exception {
        String postValue = "{\"birthDate\":\"1990-01-01\",\"city\":\"city_name\",\"country\":\"BR\",\"cards\":[{\"balance\":1000,\"cardType\":\"DRUGSTORE\",\"number\":\"4000101\"},{\"balance\":50,\"cardType\":\"FUEL\",\"number\":\"40000001\"}],\"phones\":[{\"number\":\"11111111111\",\"phoneType\":\"MOBILE\"},{\"number\":\"11985794\",\"phoneType\":\"RESIDENCE\"}],\"documentNumber\":\"1120101221\",\"email\":\"string\",\"name\":\"string\",\"number\":10,\"postalCode\":10000,\"street\":\"street name\"}";

        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isOk());
    }

    @Test
    void createConsumerInvalidCardType400() throws Exception {
        String postValue = "{\"birthDate\":\"1990-01-01\",\"city\":\"city_name\",\"country\":\"BR\",\"cards\":[{\"balance\":1000,\"cardType\":\"DRUGSTORES\",\"number\":\"4000101\"},{\"balance\":50,\"cardType\":\"FUEL\",\"number\":\"40000001\"}],\"phones\":[{\"number\":\"11111111111\",\"phoneType\":\"MOBILE\"},{\"number\":\"11985794\",\"phoneType\":\"RESIDENCE\"}],\"documentNumber\":\"000111222\",\"email\":\"string\",\"name\":\"string\",\"number\":10,\"postalCode\":10000,\"street\":\"street name\"}";

        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void createConsumerInvalidCardNumber400() throws Exception {
        String postValue = "{\"birthDate\":\"1990-01-01\",\"city\":\"city_name\",\"country\":\"BR\",\"cards\":[{\"balance\":1000,\"cardType\":\"DRUGSTORE\",\"number\":\"4000101\"},{\"balance\":50,\"cardType\":\"FUEL\",\"number\":\"40000001\"}],\"phones\":[{\"number\":\"11111111111\",\"phoneType\":\"MOBILE\"},{\"number\":\"11985794\",\"phoneType\":\"RESIDENCE\"}],\"documentNumber\":\"000111222\",\"email\":\"string\",\"name\":\"string\",\"number\":10,\"postalCode\":10000,\"street\":\"street name\"}";
        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void createConsumerInvalidDocument400() throws Exception {
        String postValue = "{\"birthDate\":\"1990-01-01\",\"city\":\"city_name\",\"country\":\"BR\",\"cards\":[{\"balance\":1000,\"cardType\":\"DRUGSTORE\",\"number\":\"4000101\"},{\"balance\":50,\"cardType\":\"FUEL\",\"number\":\"40000001\"}],\"phones\":[{\"number\":\"11111111111\",\"phoneType\":\"MOBILE\"},{\"number\":\"11985794\",\"phoneType\":\"RESIDENCE\"}],\"documentNumber\":\"1120101221\",\"email\":\"string\",\"name\":\"string\",\"number\":10,\"postalCode\":10000,\"street\":\"street name\"}";

        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void buyInvalidCardAndEstablishmentTypes400() throws Exception {

        mockMvc.perform(post("/cards/buy?cardNumber=40001041&establishmentName=Estabelecimento%20A&establishmentType=6&productDescription=tapioca&value=23")
                .contentType("application/json")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void buyCardNotFound400() throws Exception {

        mockMvc.perform(post("/cards/buy?cardNumber=402323001&establishmentName=Estabelecimento%20A&establishmentType=3&productDescription=tapioca&value=23")
                .contentType("application/json")
        ).andExpect(status().isBadRequest());
    }
    @Test
    void integrationNewConsumerSetBalanceAndBuy200() throws Exception {

        String postValue = "{\"birthDate\":\"1990-01-01\",\"city\":\"city_name\",\"country\":\"BR\",\"cards\":[{\"balance\":1000,\"cardType\":\"DRUGSTORE\",\"number\":\"000123123\"}],\"phones\":[{\"number\":\"11111111111\",\"phoneType\":\"MOBILE\"},{\"number\":\"11985794\",\"phoneType\":\"RESIDENCE\"}],\"documentNumber\":\"3789451478\",\"email\":\"string\",\"name\":\"string\",\"number\":10,\"postalCode\":10000,\"street\":\"street name\"}";

        mockMvc.perform(post("/consumers")
                .contentType("application/json")
                .content(postValue)
        ).andExpect(status().isOk());

        mockMvc.perform(patch("/cards/setcardbalance?cardNumber=000123123&value=150")
                .contentType("application/json")
        ).andExpect(status().isOk());

        mockMvc.perform(post("/cards/buy?cardNumber=000123123&establishmentName=Estabelecimento%20A&establishmentType=2&productDescription=tapioca&value=23")
                .contentType("application/json")
        ).andExpect(status().isOk());
    }
}
