package br.com.alelo.consumer.consumerpat.consumer.adapter.in;

import br.com.alelo.consumer.consumerpat.common.application.port.in.NewCard;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.AddCardCommand;
import br.com.alelo.consumer.consumerpat.consumer.application.port.in.AddCardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Cards", description = "Responsible for consumers' cards management.")
@Log4j2
@RestController
@RequestMapping(value = "/consumers/{consumerId}/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ConsumerCardController {

    private final AddCardUseCase addCardUseCase;

    @Operation(summary = "Associates card to consumer.")
    @PostMapping
    public ResponseEntity<Void> addCard(@PathVariable Integer consumerId, @Valid @RequestBody NewCard newCard) {
        var command = AddCardCommand.of(consumerId, newCard);
        addCardUseCase.addCard(command);
        return ResponseEntity.noContent().build();
    }
}
