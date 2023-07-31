package br.com.alelo.consumer.consumerpat.application.controller.consomer;

import br.com.alelo.consumer.consumerpat.application.controller.consomer.payload.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.controller.consomer.payload.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Contact;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private ConsumerService consumerService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(consumerController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testListAllConsumers_Success() throws Exception {
        // Prepare test data
        Consumer consumer = new Consumer("John Doe", "123456789", LocalDate.of(1990, 1, 1),
                new Contact("123456789", null, null, "john.doe@example.com"),
                new Address("123 Main St", 1, "City", "Country", "12345"));
        Page<Consumer> consumers = new PageImpl<>(Collections.singletonList(consumer));

        // Mock the service method
        when(consumerService.listAll(any(Pageable.class))).thenReturn(consumers);

        String formattedBirthDate = consumer.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        mockMvc.perform(get("/consumers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].consumer.name").value(consumer.getName()))
                .andExpect(jsonPath("$.content[0].consumer.documentNumber").value(consumer.getDocumentNumber()))
                //.andExpect(jsonPath("$.content[0].consumer.birthDate").value(formattedBirthDate))
                .andExpect(jsonPath("$.content[0].consumer.contact.mobilePhoneNumber").value(consumer.getContact().getMobilePhoneNumber()))
                .andExpect(jsonPath("$.content[0].consumer.address.street").value(consumer.getAddress().getStreet()));

    }

    @Test
    public void testFindConsumer_Success() throws Exception {
        // Prepare test data
        UUID consumerId = UUID.randomUUID();
        Consumer consumer = new Consumer("Jane Smith", "987654321", LocalDate.of(1985, 5, 15),
                new Contact(null, "987654321", null, "jane.smith@example.com"),
                new Address("456 Oak St", 2, "City", "Country", "54321"));

        // Mock the service method
        when(consumerService.searchConsumerById(eq(consumerId))).thenReturn(Optional.of(consumer));

        MvcResult result = mockMvc.perform(get("/consumers/{consumerId}", consumerId))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("Result: " + result.getResponse().getContentAsString());

        ConsumerResponse consumerResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ConsumerResponse.class);

        assertThat(consumerResponse.getConsumer().getName()).isEqualTo(consumer.getName());
        assertThat(consumerResponse.getConsumer().getDocumentNumber()).isEqualTo(consumer.getDocumentNumber());
        assertThat(consumerResponse.getConsumer().getBirthDate().toString()).isEqualTo(consumer.getBirthDate().toString());
        assertThat(consumerResponse.getConsumer().getContact().getResidencePhoneNumber()).isEqualTo(consumer.getContact().getResidencePhoneNumber());
    }

    @Test
    public void testCreateConsumer_Success() throws Exception {
        var newConsumerId = UUID.randomUUID();
        var contact = new Contact("555555555", null, null, "alice.johnson@example.com");
        var address = new Address("789 Elm St", 3, "City", "Country", "98765");
        var consumer = new Consumer("Alice Johnson", "555555555", LocalDate.of(1995, 7, 20), contact, address);
        consumer.addId(newConsumerId);
        ConsumerRequest consumerRequest = new ConsumerRequest(consumer);

        // Mock the service method
        when(consumerService.createConsumer(any(Consumer.class))).thenReturn(newConsumerId);

        mockMvc.perform(post("/consumers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(consumerRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(newConsumerId.toString()));
    }

    @Test
    public void testUpdateConsumer_Success() throws Exception {
        UUID consumerId = UUID.randomUUID();
        ConsumerRequest consumerRequest = new ConsumerRequest(new Consumer("Updated Name", "999999999", LocalDate.of(1980, 3, 10),
                new Contact(null, "999999999", "111111111", "updated@example.com"),
                new Address("123 Oak St", 10, "Updated City", "Updated Country", "54321")));

        mockMvc.perform(put("/consumers/{consumerId}", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consumerRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindConsumer_NotFound() throws Exception {
        UUID consumerId = UUID.randomUUID();

        when(consumerService.searchConsumerById(eq(consumerId))).thenReturn(Optional.empty());

        mockMvc.perform(get("/consumers/{consumerId}", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}
