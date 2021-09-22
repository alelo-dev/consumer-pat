package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.utils.DataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewCustomer() throws Exception {
        URI uri = new URI("/consumer/createConsumer");

        mockMvc
        .perform(MockMvcRequestBuilders
                .post(uri)
                .content(DataUtils.getJsonFromResource("create.json"))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void shouldCreateUpdateCustomer() throws Exception {
        URI uri = new URI("/consumer/updateConsumer");

        mockMvc
        .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(DataUtils.getJsonFromResource("update.json"))
                        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(404));

    }


}
