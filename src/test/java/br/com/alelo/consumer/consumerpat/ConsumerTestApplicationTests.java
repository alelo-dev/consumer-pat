package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.controller.CardController;
import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsumerTestApplicationTests {

    @Autowired
    CardController cardController;

    @Autowired
    ConsumerController consumerController;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(0)
    @DisplayName("Deve Carregar Aplicacao")
    void contextLoads() {
        assertThat(cardController).isNotNull();
        assertThat(consumerController).isNotNull();
    }

    @Test
    @Order(1)
    @DisplayName("Deve Criar Cliente")
    void mustCreateConsumer() throws Exception {

        String bodyRequest = "{\n" +
                "            \"id\": 78,\n" +
                "                \"name\": \"fake_data\",\n" +
                "                \"documentNumber\": \"fake_data\",\n" +
                "                \"birthDate\": \"2022-01-01\",\n" +
                "                \"contacts\": {\n" +
                "            \"mobilePhoneNumber\": \"fake_data\",\n" +
                "                    \"residencePhoneNumber\": \"fake_data\",\n" +
                "                    \"workPhoneNumber\": \"fake_data\",\n" +
                "                    \"email\": \"fake_data\"\n" +
                "        },\n" +
                "            \"address\": {\n" +
                "            \"street\": \"fake_data\",\n" +
                "                    \"number\": 53,\n" +
                "                    \"city\": \"fake_data\",\n" +
                "                    \"country\": \"fake_data\",\n" +
                "                    \"postalCode\": 18\n" +
                "        },\n" +
                "            \"cards\": [\n" +
                "            {\n" +
                "                \"type\": \"1\",\n" +
                "                    \"number\": 53,\n" +
                "                    \"balance\": 39.61\n" +
                "            }\n" +
                "  ]\n" +
                "        }";

        this.mockMvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    @DisplayName("Deve Listar Clientes")
    void mustListAllConsumersWith500limit() throws Exception {
        this.mockMvc.perform(get("/consumer")
                .queryParam("pageNumber", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @DisplayName("Deve Retornar Not Found")
    void mustGenerateErrorNotFound() throws Exception {
        String bodyRequest = "{\n" +
                "            \"id\": 78,\n" +
                "                \"name\": \"fake_data\",\n" +
                "                \"documentNumber\": \"fake_data\",\n" +
                "                \"birthDate\": \"2022-01-01\",\n" +
                "                \"contacts\": {\n" +
                "            \"mobilePhoneNumber\": \"fake_data\",\n" +
                "                    \"residencePhoneNumber\": \"fake_data\",\n" +
                "                    \"workPhoneNumber\": \"fake_data\",\n" +
                "                    \"email\": \"fake_data\"\n" +
                "        },\n" +
                "            \"address\": {\n" +
                "            \"street\": \"fake_data\",\n" +
                "                    \"number\": 53,\n" +
                "                    \"city\": \"fake_data\",\n" +
                "                    \"country\": \"fake_data\",\n" +
                "                    \"postalCode\": 18\n" +
                "        },\n" +
                "            \"cards\": [\n" +
                "            {\n" +
                "                \"type\": \"1\",\n" +
                "                    \"number\": 55,\n" +
                "                    \"balance\": 39.61\n" +
                "            }\n" +
                "  ]\n" +
                "        }";

        this.mockMvc.perform(put("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    @Order(4)
    @DisplayName("Deve Atualizar Cartao")
    void mustCallCardUpdate() throws Exception {
        String bodyRequest = "{\n" +
                "            \"cardNumber\": 53,\n" +
                "                \"value\": 10.00\n" +
                "        }";

        this.mockMvc.perform(put("/card/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
