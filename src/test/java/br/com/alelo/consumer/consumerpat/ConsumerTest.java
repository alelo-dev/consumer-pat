package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.dto.request.ConsumerAddressRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.ConsumerCardRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.ConsumerContactRequestDto;
import br.com.alelo.consumer.consumerpat.dto.request.ConsumerRequestDto;
import br.com.alelo.consumer.consumerpat.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerCreationService;
import br.com.alelo.consumer.consumerpat.service.ConsumerSearchService;
import br.com.alelo.consumer.consumerpat.service.ConsumerUpdateService;
import br.com.alelo.consumer.consumerpat.type.CardType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsumerTest {

    @Autowired
    ConsumerSearchService consumerSearchService;

    @Autowired
    ConsumerCreationService consumerCreationService;

    @Autowired
    ConsumerUpdateService consumerUpdateService;

    @Test
    @Order(1)
    void mustListAllConsumers() {
        List<ConsumerResponse> consumerList = consumerSearchService.listAllConsumers(1, 1);
        assertThat(consumerList, hasSize(1));
    }


    @Test
    @Order(0)
    void mustCreateConsumers() {

        ConsumerRequestDto givenConsumerRequestDto = this.createConsumerData();

        consumerCreationService.createConsumer(givenConsumerRequestDto);

        List<ConsumerResponse> consumerList = consumerSearchService.listAllConsumers(null, null);
        assertTrue(consumerList.stream()
                .anyMatch(consumers -> consumers.getName().equals(givenConsumerRequestDto.getName())));
    }

    @Test
    @Order(2)
    void mustUpdateConsumerExceptCardBalance() {

        ConsumerRequestDto givenConsumerRequestDto = this.createConsumerData();
        givenConsumerRequestDto.setId(1);

        String oldName = givenConsumerRequestDto.getName();
        givenConsumerRequestDto.setName("Novo nome");
        String newName = givenConsumerRequestDto.getName();

        Double oldBalance = givenConsumerRequestDto.getCards().get(0).getBalance();
        givenConsumerRequestDto.getCards().get(0).setBalance(12.7d);

        consumerUpdateService.updateConsumer(givenConsumerRequestDto);

        List<ConsumerResponse> consumerList = consumerSearchService.listAllConsumers(null, null);
        assertTrue(consumerList.stream()
                .anyMatch(consumers -> consumers.getName().equals(newName)));

        assertEquals(consumerList.stream()
                .filter(consumers -> consumers.getName().equals(newName))
                .mapToDouble(ConsumerResponse::getFoodCardBalance)
                .findAny()
                .orElse(-1d), oldBalance.doubleValue());
    }

    private ConsumerRequestDto createConsumerData() {
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
                .number(13)
                .balance(123.7d)
                .build());

        ConsumerRequestDto givenConsumerRequestDto = ConsumerRequestDto.builder()
                .id(null)
                .name("Created Consumer")
                .documentNumber("33333333")
                .birthDate(new Date())
                .contacts(givenContacts)
                .address(givenAddress)
                .cards(givenCards)
                .build();
        return givenConsumerRequestDto;
    }

}
