package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.request.SettlingRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/settling")
public class SettlingController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> settle(@RequestBody SettlingRequest settlingRequest) {
        return new ResponseEntity<>(cardService.updateSettlement(settlingRequest), HttpStatus.OK);
    }
}