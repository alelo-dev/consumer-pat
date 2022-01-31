package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.JsonTestUtil;
import br.com.alelo.consumer.consumerpat.domain.dto.*;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entity.Extract;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static Integer ID_ENTITY;
    private static Integer CARD_NUMBER_FOOD = 87651;
    private static Integer CARD_NUMBER_FUEL= 876542;
    private static Integer CARD_NUMBER_DRUGSTORE = 876543;
    private BigDecimal valueShopFuel = BigDecimal.valueOf(0.0).setScale(2);
    private BigDecimal valueShopDrugstore = BigDecimal.valueOf(0.0).setScale(2);
    private BigDecimal valueShopFood= BigDecimal.valueOf(0.0).setScale(2);
    private static Integer ID_ESTABLISHMENT_FUEL;
    private static Integer ID_ESTABLISHMENT_FOOD;
    private static Integer ID_ESTABLISHMENT_DRUGSTORE;


    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    @Order(1)
    void should_create_Consumer() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/consumer/createConsumer")
                .content(JsonTestUtil.asJsonString(getConsumerDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ConsumerDTO dtoResponse =  this.objectMapper.readValue(response,  ConsumerDTO.class);
        this.ID_ENTITY = dtoResponse.getId();
        assertEquals(123456, dtoResponse.getDocumentNumber());

    }

    @Test
    @Order(2)
    void should_save_cards_food_fuel_drugstore() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card")
                .content(JsonTestUtil.asJsonString(getCardFoodDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardCreateResponseDTO dtoResponse =  this.objectMapper.readValue(response,  CardCreateResponseDTO.class);
        this.CARD_NUMBER_FOOD = dtoResponse.getCardNumber();
        assertEquals(CardType.FOOD, dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_FOOD, dtoResponse.getCardNumber());



        result = mockMvc.perform(MockMvcRequestBuilders.post("/card")
                .content(JsonTestUtil.asJsonString(getCardFuelDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        dtoResponse =  this.objectMapper.readValue(response,  CardCreateResponseDTO.class);
        this.CARD_NUMBER_FUEL = dtoResponse.getCardNumber();
        assertEquals(CardType.FUEL, dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_FUEL, dtoResponse.getCardNumber());



        result = mockMvc.perform(MockMvcRequestBuilders.post("/card")
                .content(JsonTestUtil.asJsonString(getCardDrugstoreDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        dtoResponse =  this.objectMapper.readValue(response,  CardCreateResponseDTO.class);
        this.CARD_NUMBER_DRUGSTORE = dtoResponse.getCardNumber();
        assertEquals(CardType.DRUGSTORE, dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_DRUGSTORE, dtoResponse.getCardNumber());
    }

    @Test
    @Order(3)
    void should_setBalance() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{id}", CARD_NUMBER_FOOD)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO dtoResponse =  this.objectMapper.readValue(response,  CardDTO.class);


        result = mockMvc.perform(MockMvcRequestBuilders.put("/card/setcardbalance/{cardNumber}/{value}",CARD_NUMBER_FOOD,3545.0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO dtoResponseUpdate =  this.objectMapper.readValue(response,  CardDTO.class);
        BigDecimal expectedValue = BigDecimal.valueOf(6545.00).setScale(2);

        assertEquals(CARD_NUMBER_FOOD, dtoResponse.getCardNumber());
        assertEquals(expectedValue, dtoResponseUpdate.getBalanceValue());
        assertEquals(CardType.FOOD, dtoResponseUpdate.getCardType());
        assertEquals("sakura@alelo.com.br" , dtoResponseUpdate.getConsumer().getEmail());
        assertEquals("João Pessoa", dtoResponseUpdate.getConsumer().getCity());
    }

    @Test
    @Order(4)
    void should_getByCardNumber() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{cardNumber}", CARD_NUMBER_FOOD)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO dtoResponse =  this.objectMapper.readValue(response,  CardDTO.class);

        this.ID_ENTITY = dtoResponse.getCardNumber();
        assertEquals(CardType.FOOD, dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_FOOD, dtoResponse.getCardNumber());
    }

    @Test
    @Order(5)
    void should_getByCardNumber_NotFound() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{cardNumber}", 123123123)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(7)
    void should_createEstablishment_fuel_food_drugstore() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/establishment")
                .content(JsonTestUtil.asJsonString(getEstablishmentFoodDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        EstablishmentDTO dtoResponse =  this.objectMapper.readValue(response,  EstablishmentDTO.class);
        ID_ESTABLISHMENT_FOOD = dtoResponse.getId();
        assertEquals(CardType.FOOD, dtoResponse.getCardTypeAccepted());

        result = mockMvc.perform(MockMvcRequestBuilders.post("/establishment")
                .content(JsonTestUtil.asJsonString(getEstablishmentDrugstoreDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        EstablishmentDTO dtoResponseDrugstore =  this.objectMapper.readValue(response,  EstablishmentDTO.class);
        ID_ESTABLISHMENT_DRUGSTORE = dtoResponseDrugstore.getId();
        assertEquals(CardType.DRUGSTORE, dtoResponseDrugstore.getCardTypeAccepted());


        result = mockMvc.perform(MockMvcRequestBuilders.post("/establishment")
                .content(JsonTestUtil.asJsonString(getEstablishmentFuelDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        EstablishmentDTO  dtoResponseFuel =  this.objectMapper.readValue(response,  EstablishmentDTO.class);
        ID_ESTABLISHMENT_FUEL = dtoResponseFuel.getId();
        assertEquals(CardType.FUEL, dtoResponseFuel.getCardTypeAccepted());
    }


    @Test
    @Order(8)
    void should_buy_Food_Establishment() throws Exception {
        BigDecimal foodPercentDicount = BigDecimal.valueOf(0.1);
        BuyDTO food = getBuyFoodDto();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card/buy")
                .content(JsonTestUtil.asJsonString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        TransactionDTO dtoResponse =  this.objectMapper.readValue(response,  TransactionDTO.class);

        BigDecimal expectValue = food.getValue().subtract(food.getValue().multiply(foodPercentDicount));

        valueShopFood = valueShopFood.add(dtoResponse.getValue());

        assertEquals(expectValue.setScale(2), dtoResponse.getValue().setScale(2));
        assertEquals(CardType.FOOD.toString(), dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_FOOD, dtoResponse.getCardNumber());
    }

    @Test
    @Order(8)
    void should_buy_fuel_Establishment() throws Exception {
        BigDecimal foodPercentIncrease = BigDecimal.valueOf(0.35);
        BuyDTO fuelShop = getBuyFuelDto();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card/buy")
                .content(JsonTestUtil.asJsonString(fuelShop))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        TransactionDTO dtoResponse =  this.objectMapper.readValue(response,  TransactionDTO.class);

        BigDecimal expectValue = fuelShop.getValue().add(fuelShop.getValue().multiply(foodPercentIncrease));

        valueShopFuel = valueShopFuel.add(dtoResponse.getValue());

        assertEquals(expectValue.setScale(2), dtoResponse.getValue().setScale(2));
        assertEquals(CardType.FUEL.toString(), dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_FUEL, dtoResponse.getCardNumber());
    }


    @Test
    @Order(9)
    void should_buy_drugstore_Establishment() throws Exception {

        BuyDTO drugsToreShop = getBuyDrugsToreDto();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card/buy")
                .content(JsonTestUtil.asJsonString(drugsToreShop))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        TransactionDTO dtoResponse =  this.objectMapper.readValue(response,  TransactionDTO.class);

        valueShopDrugstore = valueShopDrugstore.add(dtoResponse.getValue());

        assertEquals(drugsToreShop.getValue().setScale(2), dtoResponse.getValue().setScale(2));
        assertEquals(CardType.DRUGSTORE.toString(), dtoResponse.getCardType());
        assertEquals(CARD_NUMBER_DRUGSTORE, dtoResponse.getCardNumber());
    }


    @Test
    @Order(10)
    void should_buy_with_InvalidType_FuelAndFood() throws Exception {

        BuyDTO invalidBuy = getBuyInvalidFoodFuelDto();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card/buy")
                .content(JsonTestUtil.asJsonString(invalidBuy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<ErrorDTO> errorList =  this.objectMapper.readValue(response,   new TypeReference<List<ErrorDTO>>() { });
        ErrorDTO erro = errorList.get(0);
        assertEquals("Tipo de Benefício não aceito ou não cadastrado para o estabelecimento", erro.getMessage());
    }

    @Test
    @Order(11)
    void should_getByCardNumberBalance_After_Shop() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{cardNumber}", CARD_NUMBER_FOOD)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO cardFood =  this.objectMapper.readValue(response,  CardDTO.class);

        result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{cardNumber}", CARD_NUMBER_FUEL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO cardFuel =  this.objectMapper.readValue(response,  CardDTO.class);

        result = mockMvc.perform(MockMvcRequestBuilders.get("/card/card-number/{cardNumber}", CARD_NUMBER_DRUGSTORE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CardDTO cardDrugstore =  this.objectMapper.readValue(response,  CardDTO.class);


        BigDecimal expectedValueFood = cardFood.getBalanceValue().subtract(valueShopDrugstore).setScale(2);
        BigDecimal expectedValueFuel = cardFuel.getBalanceValue().subtract(valueShopFuel).setScale(2);;
        BigDecimal expectedValueDrugstore = cardDrugstore.getBalanceValue().subtract(valueShopDrugstore).setScale(2);;

        assertEquals(expectedValueFood,cardFood.getBalanceValue());
        assertEquals(expectedValueFuel,cardFuel.getBalanceValue());
        assertEquals(expectedValueDrugstore,cardDrugstore.getBalanceValue());
    }

    @Test
    @Order(12)
    void should_buy_with_NoEnoughBalance() throws Exception {

        BuyDTO invalidBuy = getBuyNotEnoughBalanceDto();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/card/buy")
                .content(JsonTestUtil.asJsonString(invalidBuy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<ErrorDTO> errorList =  this.objectMapper.readValue(response,   new TypeReference<List<ErrorDTO>>() { });
        ErrorDTO erro = errorList.get(0);
        assertEquals("Não existe Crédito suficiente para transação", erro.getMessage());
    }

    @Test
    @Order(13)
    void should_extract() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/extract")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<Extract> extractList =  this.objectMapper.readValue(response,   new TypeReference<List<Extract>>() { });

        assertFalse(extractList.isEmpty());
        assertEquals(3,extractList.size());
    }


    private EstablishmentDTO getEstablishmentFoodDTO() {
        EstablishmentDTO dto = EstablishmentDTO.builder()
                .establishmentName("Cafeteria do Clean Code")
                .cardTypeAccepted(CardType.FOOD)
                .build();
        return dto;
    }

    private EstablishmentDTO getEstablishmentFuelDTO() {
        EstablishmentDTO dto = EstablishmentDTO.builder()
                .establishmentName("Posto do Café")
                .cardTypeAccepted(CardType.FUEL)
                .build();
        return dto;
    }

    private EstablishmentDTO getEstablishmentDrugstoreDTO() {
        EstablishmentDTO dto = EstablishmentDTO.builder()
                .establishmentName("Farma Bugs")
                .cardTypeAccepted(CardType.DRUGSTORE)
                .build();
        return dto;
    }

    private BuyDTO getBuyFoodDto() {
        BuyDTO dto = BuyDTO.builder()
                .cardNumber(CARD_NUMBER_FOOD)
                .establishmentType(CardType.FOOD.getCardTypeId())
                .productDescription("Pastel do Japa")
                .establishmentId(ID_ESTABLISHMENT_FOOD)
                .value(BigDecimal.valueOf(600.00).setScale(2))
                .build();

        return dto;
    }

    private BuyDTO getBuyFuelDto() {
        BuyDTO dto = BuyDTO.builder()
                .cardNumber(CARD_NUMBER_FUEL)
                .establishmentType(CardType.FUEL.getCardTypeId())
                .productDescription("5 litros de gasolina")
                .establishmentId(ID_ESTABLISHMENT_FUEL)
                .value(BigDecimal.valueOf(1000.00).setScale(2))
                .build();
        return dto;
    }

    private BuyDTO getBuyDrugsToreDto() {
        BuyDTO dto = BuyDTO.builder()
                .cardNumber(CARD_NUMBER_DRUGSTORE)
                .establishmentType(CardType.DRUGSTORE.getCardTypeId())
                .productDescription("Dorflex 500")
                .establishmentId(ID_ESTABLISHMENT_DRUGSTORE)
                .value(BigDecimal.valueOf(545.00).setScale(2))
                .build();
        return dto;
    }

    private BuyDTO getBuyInvalidFoodFuelDto() {
        BuyDTO dto = BuyDTO.builder()
                .cardNumber(CARD_NUMBER_DRUGSTORE)
                .establishmentType(CardType.FOOD.getCardTypeId())
                .productDescription("Pastel do Japa")
                .establishmentId(ID_ESTABLISHMENT_FUEL)
                .value(BigDecimal.valueOf(600.00).setScale(2))
                .build();

        return dto;
    }

    private BuyDTO getBuyNotEnoughBalanceDto() {
        BuyDTO dto = BuyDTO.builder()
                .cardNumber(CARD_NUMBER_FOOD)
                .establishmentType(CardType.FOOD.getCardTypeId())
                .productDescription("Camarão de Marte")
                .establishmentId(ID_ESTABLISHMENT_FOOD)
                .value(BigDecimal.valueOf(9000.00).setScale(2))
                .build();
        return dto;
    }

    private CardDTO getCardFoodDto() {
        CardDTO dto = CardDTO.builder()
                .cardType(CardType.FOOD)
                .cardNumber(CARD_NUMBER_FOOD)
                .balanceValue(BigDecimal.valueOf(3000.0))
                .consumer(getConsumer())
                .build();

        return dto;
    }

    private CardDTO getCardFuelDto() {
        CardDTO dto = CardDTO.builder()
                .cardType(CardType.FUEL)
                .cardNumber(CARD_NUMBER_FUEL)
                .balanceValue(BigDecimal.valueOf(3000.0))
                .consumer(getConsumer())
                .build();

        return dto;
    }

    private CardDTO getCardDrugstoreDto() {
        CardDTO dto = CardDTO.builder()
                .cardType(CardType.DRUGSTORE)
                .cardNumber(CARD_NUMBER_DRUGSTORE)
                .balanceValue(BigDecimal.valueOf(3000.0))
                .consumer(getConsumer())
                .build();

        return dto;
    }

    private Consumer getConsumer() {
        LocalDate birthDate =  LocalDate.of(1982,12,17);
        Consumer consumer = Consumer.builder()
                .id(ID_ENTITY)
                .birthDate(Date.from(birthDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .city("João Pessoa")
                .country("Brazil")
                .documentNumber(123456)
                .name("Sakura Card Captors")
                .street("Rua da mansão Bly")
                .number(41)
                .email("sakura@alelo.com.br")
                .portalCode(45850000)
                .phoneNumber("88888888")
                .residencePhoneNumber("99999999")
                .mobilePhoneNumber("77777777")
                .build();

        return consumer;
    }

    private ConsumerDTO getConsumerDTO() {
        LocalDate birthDate =  LocalDate.of(1982,12,17);
        ConsumerDTO dto = ConsumerDTO.builder()
                .birthDate(Date.from(birthDate.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .city("João Pessoa")
                .country("Brazil")
                .documentNumber(123456)
                .name("Sakura Card Captors")
                .street("Rua da mansão Bly")
                .number(41)
                .email("sakura@alelo.com.br")
                .portalCode(45850000)
                .phoneNumber("88888888")
                .residencePhoneNumber("99999999")
                .mobilePhoneNumber("77777777")
                .build();

        return dto;
    }

}