package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.CardRequest;
import br.com.alelo.consumer.consumerpat.dto.response.CardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
@RequiredArgsConstructor
@Api(value = "/card", description = "Operations about benefit cards")
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    @PutMapping(value = "/updateConsumer")
    @ResponseStatus(code = HttpStatus.OK, reason = "Update Sucessful")
    @ApiOperation(value = "Update customer card informations", response = CardDto.class)
    public ResponseEntity<CardDto> update(@RequestBody CardDto cardDto) throws Exception {
        Card card = cardService.update(cardDto);
        return ResponseEntity.ok(cardMapper.toDto(card));
    }

    @PatchMapping(value = "/creditCard")
    @ResponseStatus(code = HttpStatus.OK, reason = "Credit Sucessful")
    @ApiOperation(value = "Add a value in card", response = CardDto.class)
    public ResponseEntity<CardDto> credit(@RequestBody CardRequest cardRequest, @RequestParam double value) throws Exception {
        Card card = cardService.findByTypeAndNumber(cardRequest.getType(), cardRequest.getNumber());
        cardService.credit(card, value);
        return ResponseEntity.ok(cardMapper.toDto(card));
    }

}
