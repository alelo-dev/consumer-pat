package br.com.alelo.consumer.consumerpat.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import br.com.alelo.consumer.consumerpat.controller.dto.CardSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardUpdateDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerSaveDto;
import br.com.alelo.consumer.consumerpat.model.type.CardType;

@SpringBootTest
public class CardControllerTests {

    @Autowired
    private CardController     cardController;
    @Autowired
    private ConsumerController consumerController;

    @Test
    public void createCard() throws Exception {

        var consumer = ConsumerSaveDto.builder().birthDate(LocalDate.now()).documentNumber("12341234").name("teste")
                .build();

        var c    = consumerController.createConsumer(consumer);
        var card = CardSaveDto.builder().balance(new BigDecimal(10)).consumerId(c.getId()).number("1234-1234-1234-1234")
                .type(CardType.DRUGSTTORE.name()).build();

        cardController.createCard(card);

    }

    @Test
    public void updateCardWithoutBalance() throws Exception {
        var consumer = ConsumerSaveDto.builder().birthDate(LocalDate.now()).documentNumber("12341234").name("teste")
                .build();

        var c           = consumerController.createConsumer(consumer);
        var cardDtoSave = CardSaveDto.builder().balance(new BigDecimal(10)).consumerId(c.getId())
                .number("1234-1234-1234-1234").type(CardType.DRUGSTTORE.name()).build();

        cardController.createCard(cardDtoSave);

        var consumers = consumerController.listAll(PageRequest.of(0, 1));
        var card      = consumers.getItems().get(0).getCards().stream().findFirst().get();

        var update = CardUpdateDto.builder().id(card.getId()).consumerId(card.getConsumerId())
                .number("1234-1234-1234-12344").type(CardType.FOOD.name()).build();

        var previewBalance = BigDecimal.ZERO.add(cardDtoSave.getBalance());

        cardController.updateCard(update);

        consumers = consumerController.listAll(PageRequest.of(0, 1));
        var cardUpdated = consumers.getItems().get(0).getCards().stream().findFirst().get();

        var balanceIsEquals = previewBalance.equals(cardUpdated.getBalance());

        assertThat(balanceIsEquals).isTrue();

    }

}