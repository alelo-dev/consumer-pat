package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.*;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.utils.Constants;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.ConsumerFilterVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.NewConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.consumer.UpdateConsumerFormVO;
import br.com.alelo.consumer.consumerpat.web.vo.extract.NewExtractFormVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsumerControllerTest {

    private static final String NOT_FOUND_MESSAGE = "Consumer not found!";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_PATTERN);
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT_PATTERN);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsumerService consumerService;

    @MockBean
    private ExtractService extractService;

    @Test
    void findById_WithInvalidID_ShouldReturnConsumerNotFound() throws Exception {
        given(consumerService.findById(CONSUMER_INVALID_ID))
            .willThrow(new ResourceNotFoundException(NOT_FOUND_MESSAGE));

        mockMvc.perform(get("/v1/consumer/{consumerId}", CONSUMER_INVALID_ID)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors", hasSize(0)))
            .andExpect(jsonPath("$.details", is(NOT_FOUND_MESSAGE)));
    }

    @Test
    void findById_WithValidID_ShouldReturnCorrespondingConsumer() throws Exception {
        Consumer consumer = buildConsumer();

        given(consumerService.findById(consumer.getId())).willReturn(consumer);

        mockMvc.perform(get("/v1/consumer/{consumerId}", consumer.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(consumer.getId().intValue())))
            .andExpect(jsonPath("$.name", is(consumer.getName())))
            .andExpect(jsonPath("$.documentNumber", is(consumer.getDocumentNumber())))
            .andExpect(jsonPath("$.birthDate", is(consumer.getBirthDate().format(DATE_FORMATTER))));
    }

    @Test
    void findAll_WithEmptyResult_ShouldReturnEmptyResultInPaginatedList() throws Exception {
        given(consumerService.findAll(any(ConsumerFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of()));

        mockMvc.perform(get("/v1/consumer?page=0&size=10&sortDirection=ASC&sortField=ID")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.records").exists())
            .andExpect(jsonPath("$.records").isArray())
            .andExpect(jsonPath("$.records", hasSize(0)))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.hasNext", is(false)))
            .andExpect(jsonPath("$.page.hasPrev", is(false)))
            .andExpect(jsonPath("$.page.page", is(0)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.size", is(0)))
            .andExpect(jsonPath("$.page.totalElements", is(0)));
    }

    @Test
    void findAll_WithNonEmptyResult_ShouldReturnConsumerInPaginatedList() throws Exception {
        Consumer consumer = buildConsumer();

        given(consumerService.findAll(any(ConsumerFilterVO.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(consumer)));

        mockMvc.perform(get("/v1/consumer?page=0&size=10&sortDirection=ASC&sortField=ID")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.records").exists())
            .andExpect(jsonPath("$.records").isArray())
            .andExpect(jsonPath("$.records", hasSize(1)))
            .andExpect(jsonPath("$.records[0].id", is(consumer.getId().intValue())))
            .andExpect(jsonPath("$.records[0].name", is(consumer.getName())))
            .andExpect(jsonPath("$.records[0].documentNumber", is(consumer.getDocumentNumber())))
            .andExpect(jsonPath("$.records[0].birthDate", is(consumer.getBirthDate().format(DATE_FORMATTER))))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.hasNext", is(false)))
            .andExpect(jsonPath("$.page.hasPrev", is(false)))
            .andExpect(jsonPath("$.page.page", is(0)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.size", is(1)))
            .andExpect(jsonPath("$.page.totalElements", is(1)));
    }

    @Test
    void save_WithoutMandatoryParameters_ShouldReturnInvalidValueMessages() throws Exception {
        NewConsumerFormVO body = new NewConsumerFormVO();

        mockMvc.perform(post("/v1/consumer")
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors", aMapWithSize(8)))
            .andExpect(jsonPath("$.errors", hasEntry("name", "Name cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("documentNumber", "Document cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("birthDate", "BirthDate cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("street", "Street cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("number", "Number cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("city", "City cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("country", "Country cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("postalCode", "PostalCode cannot be null")))
            .andExpect(jsonPath("$.details", is("uri=/v1/consumer")));
    }

    @Test
    void save_WithAllMandatoryParameters_ShouldPersistConsumerAndAddressData() throws Exception {
        Address address = buildAddress();
        Contact contact = buildContact();

        Consumer consumer = buildConsumer();
        consumer.setAddress(address);
        consumer.setContacts(Set.of(contact));

        NewConsumerFormVO body = buildNewConsumerFormVo(consumer);

        given(consumerService.save(any(NewConsumerFormVO.class))).willReturn(consumer);

        mockMvc.perform(post("/v1/consumer")
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(consumer.getId().intValue())))
            .andExpect(jsonPath("$.name", is(consumer.getName())))
            .andExpect(jsonPath("$.documentNumber", is(consumer.getDocumentNumber())))
            .andExpect(jsonPath("$.birthDate", is(consumer.getBirthDate().format(DATE_FORMATTER))))
            .andExpect(jsonPath("$.address").exists())
            .andExpect(jsonPath("$.address.id", is(address.getId().intValue())))
            .andExpect(jsonPath("$.address.street", is(address.getStreet())))
            .andExpect(jsonPath("$.address.number", is(address.getNumber())))
            .andExpect(jsonPath("$.address.city", is(address.getCity())))
            .andExpect(jsonPath("$.address.country", is(address.getCountry())))
            .andExpect(jsonPath("$.address.postalCode", is(address.getPostalCode().intValue())))
            .andExpect(jsonPath("$.contacts").exists())
            .andExpect(jsonPath("$.contacts", hasSize(1)))
            .andExpect(jsonPath("$.contacts[0].id", is(contact.getId().intValue())))
            .andExpect(jsonPath("$.contacts[0].type", is(contact.getType().name())))
            .andExpect(jsonPath("$.contacts[0].value", is(contact.getValue())))
            .andExpect(jsonPath("$.cards").exists())
            .andExpect(jsonPath("$.cards", hasSize(0)))
            .andExpect(jsonPath("$.extracts").exists())
            .andExpect(jsonPath("$.extracts", hasSize(0)));
    }

    @Test
    void update_WithoutMandatoryParameters_ShouldReturnInvalidValueMessages() throws Exception {
        UpdateConsumerFormVO body = new UpdateConsumerFormVO(null, null, null);

        mockMvc.perform(put("/v1/consumer/{consumerId}", CONSUMER_ID)
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors", aMapWithSize(3)))
            .andExpect(jsonPath("$.errors", hasEntry("name", "Name cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("documentNumber", "Document cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("birthDate", "BirthDate cannot be null")))
            .andExpect(jsonPath("$.details", is("uri=/v1/consumer/" + CONSUMER_ID)));
    }

    @Test
    void update_WithAllMandatoryParameters_ShouldUpdateConsumerData() throws Exception {
        Consumer consumer = buildConsumer();
        consumer.setAddress(buildAddress());

        given(consumerService.update(eq(consumer.getId()), any(UpdateConsumerFormVO.class))).willReturn(consumer);

        UpdateConsumerFormVO body = buildUpdateConsumerFormVO(consumer);

        mockMvc.perform(put("/v1/consumer/{consumerId}", consumer.getId())
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(consumer.getId().intValue())))
            .andExpect(jsonPath("$.name", is(consumer.getName())))
            .andExpect(jsonPath("$.documentNumber", is(consumer.getDocumentNumber())))
            .andExpect(jsonPath("$.birthDate", is(consumer.getBirthDate().format(DATE_FORMATTER))));
    }

    @Test
    void buy_WithoutMandatoryParameters_ShouldReturnInvalidValueMessages() throws Exception {
        NewExtractFormVO body = new NewExtractFormVO();

        mockMvc.perform(post("/v1/consumer/buy")
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors", aMapWithSize(6)))
            .andExpect(jsonPath("$.errors", hasEntry("cardNumber", "CardNumber cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("productDescription", "ProductDescription cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("establishmentId", "EstablishmentId cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("establishmentName", "EstablishmentName cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("establishmentType", "EstablishmentType cannot be null")))
            .andExpect(jsonPath("$.errors", hasEntry("value", "Value cannot be null")))
            .andExpect(jsonPath("$.details", is("uri=/v1/consumer/buy")));
    }

    @Test
    void buy_WithAllMandatoryParameters_ShouldPersistConsumerAndAddressData() throws Exception {
        Card card = buildCard(EstablishmentType.DRUGSTORE, null);
        Extract extract = buildExtract(card, null);

        given(extractService.save(any(NewExtractFormVO.class))).willReturn(extract);

        NewExtractFormVO body = buildExtractFormVo(extract, EstablishmentType.FOOD);

        mockMvc.perform(post("/v1/consumer/buy")
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.establishmentId", is(extract.getEstablishmentId().intValue())))
            .andExpect(jsonPath("$.establishmentName", is(extract.getEstablishmentName())))
            .andExpect(jsonPath("$.productDescription", is(extract.getProductDescription())))
            .andExpect(jsonPath("$.dateBuy", is(extract.getDateBuy().format(DATETIME_FORMATTER))))
            .andExpect(jsonPath("$.value", is(extract.getValue().doubleValue())));
    }

}