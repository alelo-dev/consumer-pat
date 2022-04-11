package br.com.alelo.consumer.consumerpat.controllers;

import br.com.alelo.consumer.consumerpat.dtos.ExtractDto;
import br.com.alelo.consumer.consumerpat.services.ExtractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class ExtractController {

    final ExtractService extractService;

    public ExtractController(ExtractService extractService) {
        this.extractService = extractService;
    }

    @PostMapping
    public ResponseEntity<Object> buy(@RequestBody ExtractDto extractDto) {
        return ResponseEntity.status(HttpStatus.OK).body(extractService.buy(extractDto));
    }
}
