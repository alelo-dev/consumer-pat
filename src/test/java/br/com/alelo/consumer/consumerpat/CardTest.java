package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.dto.request.*;
import br.com.alelo.consumer.consumerpat.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.CardBalanceUpdateService;
import br.com.alelo.consumer.consumerpat.service.CardPurchaseService;
import br.com.alelo.consumer.consumerpat.service.ConsumerCreationService;
import br.com.alelo.consumer.consumerpat.service.ConsumerSearchService;
import br.com.alelo.consumer.consumerpat.type.CardType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardTest {

    @Autowired
    ConsumerSearchService consumerSearchService;

    @Autowired
    CardBalanceUpdateService cardBalanceUpdateService;

    @Autowired
    CardPurchaseService cardPurchaseService;

    @BeforeAll
    public static void setup(@Autowired ConsumerCreationService consumerCreationService) {

        ConsumerContactRequestDto givenContacts = ConsumerContactRequestDto.builder()
                .mobilePhoneNumber("119833333")
                .residencePhoneNumber("113333333")
                .workPhoneNumber("1123444444")
                .email("created@email.com")
                .build();

        ConsumerAddressRequestDto givenAddress = ConsumerAddressRequestDto.builder()
                .street("Rua do teste")
                .number(10)
                .city("Itu")
                .country("Brasil")
                .postalCode(123456)
                .build();

        List<ConsumerCardRequestDto> givenCards = Arrays.asList(ConsumerCardRequestDto.builder()
                        .type(CardType.FOOD)
                        .number(14)
                        .balance(100.0d)
                        .build(),
                ConsumerCardRequestDto.builder()
                        .type(CardType.FUEL)
                        .number(34)
                        .balance(100.0d)
                        .build(),
                ConsumerCardRequestDto.builder()
                        .type(CardType.DRUGSTORE)
                        .number(24)
                        .balance(100.0d)
                        .build()
        );

        ConsumerRequestDto givenConsumerRequestDto = ConsumerRequestDto.builder()
                .id(null)
                .name("Created Consumer")
                .documentNumber("33333333")
                .birthDate(new Date())
                .contacts(givenContacts)
                .address(givenAddress)
                .cards(givenCards)
                .build();

        consumerCreationService.createConsumer(givenConsumerRequestDto);
    }

    @Test
    void mustUpdateCardBalance() {
        ConsumerResponse existingConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .findFirst()
                .orElse(null);

        Double oldCardBalance = existingConsumer.getFoodCardBalance();

        Double increaseBalanceValue = 10.2d;

        Double expectedCardBalance = oldCardBalance + increaseBalanceValue;

        cardBalanceUpdateService.setBalance(CardBalanceUpdateRequestDto.builder()
                .cardNumber(existingConsumer.getFoodCardNumber())
                .value(increaseBalanceValue)
                .build());

        ConsumerResponse updatedConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .filter(consumers -> consumers.getName().equals(existingConsumer.getName()))
                .findFirst()
                .orElse(null);

        assertEquals(updatedConsumer.getFoodCardBalance(), expectedCardBalance);
    }


    @Test
    void mustDebitCardForDrugstoreEstabilishment() {
        ConsumerResponse existingConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .findFirst()
                .orElse(null);

        Double oldCardBalance = existingConsumer.getDrugstoreCardBalance();
        Double purchaseValue = 10.0d;

        cardPurchaseService.buy(CardPurchaseRequestDto.builder()
                .cardNumber(existingConsumer.getDrugstoreCardNumber())
                .establishmentType(2)
                .establishmentName("FAMACIA ZEZINHO")
                .productDescription("ASPIRINA")
                .value(purchaseValue)
                .build());

        Double expectedCardBalance = oldCardBalance - purchaseValue;

        ConsumerResponse updatedConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .filter(consumers -> consumers.getName().equals(existingConsumer.getName()))
                .findFirst()
                .orElse(null);

        assertEquals(updatedConsumer.getDrugstoreCardBalance(), expectedCardBalance);
    }


    @Test
    void mustDebitCardForFoodEstabilishmentWithCashbackDiscount() {
        ConsumerResponse existingConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .findFirst()
                .orElse(null);

        Double oldCardBalance = existingConsumer.getFoodCardBalance();
        Double purchaseValue = 10.0d;
        cardPurchaseService.buy(CardPurchaseRequestDto.builder()
                .cardNumber(existingConsumer.getFoodCardNumber())
                .establishmentType(1)
                .establishmentName("Comida Caseira")
                .productDescription("Arroz e Feijao")
                .value(purchaseValue)
                .build());

        Double expectedCashbackValue = purchaseValue * 0.10;
        Double expectedDiscount = purchaseValue - expectedCashbackValue;
        Double expectedCardBalance = oldCardBalance - expectedDiscount;

        ConsumerResponse updatedConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .filter(consumers -> consumers.getName().equals(existingConsumer.getName()))
                .findFirst()
                .orElse(null);

        assertEquals(updatedConsumer.getFoodCardBalance(), expectedCardBalance);
    }


    @Test
    void mustDebitCardForFuelEstabilishmentWithTaxCharges() {
        ConsumerResponse existingConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .findFirst()
                .orElse(null);

        Double oldCardBalance = existingConsumer.getFuelCardBalance();
        Double purchaseValue = 10.0d;

        Double expectedTax = purchaseValue/100 * 35;
        Double expectedCharge = purchaseValue + expectedTax;
        Double expectedCardBalance = oldCardBalance - expectedCharge;

        cardPurchaseService.buy(CardPurchaseRequestDto.builder()
                .cardNumber(existingConsumer.getFuelCardNumber())
                .establishmentType(3)
                .establishmentName("Posto do Pedro")
                .productDescription("Gasolina")
                .value(purchaseValue)
                .build());

        ConsumerResponse updatedConsumer = consumerSearchService.listAllConsumers(null, null)
                .stream()
                .filter(consumers -> consumers.getName().equals(existingConsumer.getName()))
                .findFirst()
                .orElse(null);

        assertEquals(updatedConsumer.getFuelCardBalance(), expectedCardBalance);
    }

}
