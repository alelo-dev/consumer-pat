package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.constants.ErrorCodeEnum;
import br.com.alelo.consumer.consumerpat.constants.ErrorMessages;
import br.com.alelo.consumer.consumerpat.constants.ValidationConstraints;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exceptions.PurchaseException;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static br.com.alelo.consumer.consumerpat.constants.TestConstants.getConsumer;
import static br.com.alelo.consumer.consumerpat.constants.TestConstants.getExtract;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_BASIC_REQUEST_MAPPING;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_BUY;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_CREATE_CONSUMER;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_LIST_CONSUMERS;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_SET_CARD_BALANCE;
import static br.com.alelo.consumer.consumerpat.constants.UrlConstants.URI_UPDATE_CONSUMER;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@EnableSpringDataWebSupport
public class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumerService service;

    @Test
    public void getAllCustomersShouldReturnOkStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_LIST_CONSUMERS)
                .build()
                .getPath();

        assert path != null;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Consumer consumer = getConsumer();

        //when
        when(this.service.listAllConsumers())
                .thenReturn(Collections.singletonList(consumer));

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", equalTo(consumer.getName())))
                .andExpect(jsonPath("$.[0].documentNumber", equalTo(consumer.getDocumentNumber())))
                .andExpect(jsonPath("$.[0].contact.mobilePhoneNumber", equalTo(consumer.getContact().getMobilePhoneNumber())))
                .andExpect(jsonPath("$.[0].contact.residencePhoneNumber", equalTo(consumer.getContact().getResidencePhoneNumber())))
                .andExpect(jsonPath("$.[0].contact.phoneNumber", equalTo(consumer.getContact().getPhoneNumber())))
                .andExpect(jsonPath("$.[0].contact.email", equalTo(consumer.getContact().getEmail())))
                .andExpect(jsonPath("$.[0].address.street", equalTo(consumer.getAddress().getStreet())))
                .andExpect(jsonPath("$.[0].address.number", equalTo(consumer.getAddress().getNumber())))
                .andExpect(jsonPath("$.[0].address.city", equalTo(consumer.getAddress().getCity())))
                .andExpect(jsonPath("$.[0].address.country", equalTo(consumer.getAddress().getCountry())))
                .andExpect(jsonPath("$.[0].address.portalCode", equalTo(consumer.getAddress().getPortalCode())))
                .andExpect(jsonPath("$.[0].card.foodCardNumber", equalTo(consumer.getCard().getFoodCardNumber())))
                .andExpect(jsonPath("$.[0].card.foodCardBalance", equalTo(consumer.getCard().getFoodCardBalance())))
                .andExpect(jsonPath("$.[0].card.fuelCardNumber", equalTo(consumer.getCard().getFuelCardNumber())))
                .andExpect(jsonPath("$.[0].card.fuelCardBalance", equalTo(consumer.getCard().getFuelCardBalance())))
                .andExpect(jsonPath("$.[0].card.drugstoreCardNumber", equalTo(consumer.getCard().getDrugstoreCardNumber())))
                .andExpect(jsonPath("$.[0].card.drugstoreCardBalance", equalTo(consumer.getCard().getDrugstoreCardBalance())));

    }

    @Test
    public void createConsumerShouldReturnCreatedStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_CREATE_CONSUMER)
                .build()
                .getPath();

        assert path != null;

        ObjectMapper objectMapper = new ObjectMapper();

        Consumer consumer = getConsumer();
        String consumerJSON = objectMapper.writeValueAsString(consumer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(consumerJSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.createConsumer(any(Consumer.class)))
                .thenReturn(consumer);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(consumer.getName())))
                .andExpect(jsonPath("$.documentNumber", equalTo(consumer.getDocumentNumber())))
                .andExpect(jsonPath("$.contact.mobilePhoneNumber", equalTo(consumer.getContact().getMobilePhoneNumber())))
                .andExpect(jsonPath("$.contact.residencePhoneNumber", equalTo(consumer.getContact().getResidencePhoneNumber())))
                .andExpect(jsonPath("$.contact.phoneNumber", equalTo(consumer.getContact().getPhoneNumber())))
                .andExpect(jsonPath("$.contact.email", equalTo(consumer.getContact().getEmail())))
                .andExpect(jsonPath("$.address.street", equalTo(consumer.getAddress().getStreet())))
                .andExpect(jsonPath("$.address.number", equalTo(consumer.getAddress().getNumber())))
                .andExpect(jsonPath("$.address.city", equalTo(consumer.getAddress().getCity())))
                .andExpect(jsonPath("$.address.country", equalTo(consumer.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.portalCode", equalTo(consumer.getAddress().getPortalCode())))
                .andExpect(jsonPath("$.card.foodCardNumber", equalTo(consumer.getCard().getFoodCardNumber())))
                .andExpect(jsonPath("$.card.foodCardBalance", equalTo(consumer.getCard().getFoodCardBalance())))
                .andExpect(jsonPath("$.card.fuelCardNumber", equalTo(consumer.getCard().getFuelCardNumber())))
                .andExpect(jsonPath("$.card.fuelCardBalance", equalTo(consumer.getCard().getFuelCardBalance())))
                .andExpect(jsonPath("$.card.drugstoreCardNumber", equalTo(consumer.getCard().getDrugstoreCardNumber())))
                .andExpect(jsonPath("$.card.drugstoreCardBalance", equalTo(consumer.getCard().getDrugstoreCardBalance())));
    }

    @Test
    public void updateConsumerShouldReturnOkStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_UPDATE_CONSUMER)
                .build()
                .getPath();

        assert path != null;

        ObjectMapper objectMapper = new ObjectMapper();

        Consumer consumer = getConsumer();
        String consumerJSON = objectMapper.writeValueAsString(consumer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(consumerJSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.updateConsumer(any(Consumer.class)))
                .thenReturn(consumer);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(consumer.getName())))
                .andExpect(jsonPath("$.documentNumber", equalTo(consumer.getDocumentNumber())))
                .andExpect(jsonPath("$.contact.mobilePhoneNumber", equalTo(consumer.getContact().getMobilePhoneNumber())))
                .andExpect(jsonPath("$.contact.residencePhoneNumber", equalTo(consumer.getContact().getResidencePhoneNumber())))
                .andExpect(jsonPath("$.contact.phoneNumber", equalTo(consumer.getContact().getPhoneNumber())))
                .andExpect(jsonPath("$.contact.email", equalTo(consumer.getContact().getEmail())))
                .andExpect(jsonPath("$.address.street", equalTo(consumer.getAddress().getStreet())))
                .andExpect(jsonPath("$.address.number", equalTo(consumer.getAddress().getNumber())))
                .andExpect(jsonPath("$.address.city", equalTo(consumer.getAddress().getCity())))
                .andExpect(jsonPath("$.address.country", equalTo(consumer.getAddress().getCountry())))
                .andExpect(jsonPath("$.address.portalCode", equalTo(consumer.getAddress().getPortalCode())))
                .andExpect(jsonPath("$.card.foodCardNumber", equalTo(consumer.getCard().getFoodCardNumber())))
                .andExpect(jsonPath("$.card.foodCardBalance", equalTo(consumer.getCard().getFoodCardBalance())))
                .andExpect(jsonPath("$.card.fuelCardNumber", equalTo(consumer.getCard().getFuelCardNumber())))
                .andExpect(jsonPath("$.card.fuelCardBalance", equalTo(consumer.getCard().getFuelCardBalance())))
                .andExpect(jsonPath("$.card.drugstoreCardNumber", equalTo(consumer.getCard().getDrugstoreCardNumber())))
                .andExpect(jsonPath("$.card.drugstoreCardBalance", equalTo(consumer.getCard().getDrugstoreCardBalance())));
    }

    @Test
    public void updateConsumerShouldReturnNotFoundStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_UPDATE_CONSUMER)
                .build()
                .getPath();

        assert path != null;

        ObjectMapper objectMapper = new ObjectMapper();

        Consumer consumer = getConsumer();
        String consumerJSON = objectMapper.writeValueAsString(consumer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(consumerJSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.updateConsumer(any(Consumer.class)))
                .thenThrow(new PurchaseException(
                        ErrorCodeEnum.CONSUMER_NOT_FOUND,
                        ErrorMessages.NOT_FOUND,
                        ValidationConstraints.CONSUMER_NOT_FOUND_BY_ID));

        //then
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    public void updateConsumerShouldReturnUnauthorizedStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_UPDATE_CONSUMER)
                .build()
                .getPath();

        assert path != null;

        ObjectMapper objectMapper = new ObjectMapper();

        Consumer consumer = getConsumer();
        String consumerJSON = objectMapper.writeValueAsString(consumer);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(consumerJSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.updateConsumer(any(Consumer.class)))
                .thenThrow(new PurchaseException(
                        ErrorCodeEnum.BALANCE_UPDATE_NOT_ALLOWED,
                        ErrorMessages.UNAUTHORIZED,
                        ValidationConstraints.BALANCE_UPDATE_NOT_ALLOWED));

        //then
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    public void setCardBalanceShouldReturnOkStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_SET_CARD_BALANCE)
                .buildAndExpand()
                .getPath();

        assert path != null;

        Consumer consumer = getConsumer();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path)
                .param("cardNumber", "123456")
                .param("value", "500")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.setCardBalance(anyInt(), anyDouble()))
                .thenReturn(consumer);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void buyShouldReturnOkStatus() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_BASIC_REQUEST_MAPPING + URI_BUY)
                .buildAndExpand()
                .getPath();

        assert path != null;

        Extract extract = getExtract();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path)
                .param("establishmentType", "1")
                .param("establishmentName", "Store")
                .param("cardNumber", "123456")
                .param("productDescription", "Description")
                .param("value", "50")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.buy(anyInt(), anyString(), anyInt(), anyString(), anyDouble()))
                .thenReturn(extract);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}
