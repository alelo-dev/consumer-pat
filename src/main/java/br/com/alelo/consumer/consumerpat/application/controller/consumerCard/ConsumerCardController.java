package br.com.alelo.consumer.consumerpat.application.controller.consumerCard;

import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.CardConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.CardConsumerResponse;
import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.payload.dto.CardsDto;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping(value = "/consumers/{consumerId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumerCardController {

    private final CardService cardService;

    public ConsumerCardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Void> addCard(@PathVariable UUID consumerId, @Valid @RequestBody CardConsumerRequest cardRequest) {
        cardService.addCard(consumerId, cardRequest.getCard());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CardConsumerResponse> listCardByConsumerId(@PathVariable UUID consumerId) {
        var cardsFound = cardService.searchCardByConsumerId(consumerId);

        var cardBalanceDtoSet = cardsFound.map(cards -> cards.stream().map(card ->
                CardsDto.of(card, card.getCardBalance().getAmount())).collect(Collectors.toSet())).get();

        return cardsFound.map(cards -> ResponseEntity.ok(CardConsumerResponse.of(cardBalanceDtoSet)))
                .orElse(ResponseEntity.notFound().build());
    }
}
