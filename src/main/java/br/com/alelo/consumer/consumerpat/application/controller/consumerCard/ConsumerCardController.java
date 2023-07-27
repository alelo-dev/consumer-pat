package br.com.alelo.consumer.consumerpat.application.controller.consumerCard;

import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.request.CardRequest;
import br.com.alelo.consumer.consumerpat.application.controller.consumerCard.response.CardResponse;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(value = "/consumers/{consumerId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumerCardController {

    private final CardService cardService;

    public ConsumerCardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Void> addCard(@PathVariable UUID consumerId, @Valid @RequestBody CardRequest cardRequest) {
        cardService.addCard(consumerId, cardRequest.getCard());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CardResponse> listCardByConsumerId(@PathVariable UUID consumerId) {
        var cardsFound = cardService.searchCardByConsumerId(consumerId);
        return cardsFound.map(cards -> ResponseEntity.ok(CardResponse.of(cards)))
                .orElse(ResponseEntity.notFound().build());
    }
}
