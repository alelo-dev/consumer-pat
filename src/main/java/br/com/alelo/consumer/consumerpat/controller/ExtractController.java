package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractService extractService;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Void> buy(@RequestBody Extract request) {

        extractService.buy(request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cardNumber}").buildAndExpand(request.getCardNumber()).toUri();

        return ResponseEntity.created(location).build();
    }
}
