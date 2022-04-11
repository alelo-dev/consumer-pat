package br.com.alelo.consumer.consumerpat.controllers;

import br.com.alelo.consumer.consumerpat.models.enums.EstablishmentEnum;
import br.com.alelo.consumer.consumerpat.services.CardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setcardbalance")
public class CardsController {

    final CardsService cardsService;

    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping
    public ResponseEntity<Object> setBalance(EstablishmentEnum establishmentEnum, int cardNumber, double value) {
        return ResponseEntity.ok(cardsService.setBalance(establishmentEnum, cardNumber, value));
    }

}
