package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.ReloadCardDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    /*
     * Deve creditar (adicionar) um valor (value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping("/reloadCard")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reloadCard(@RequestBody ReloadCardDTO reloadCardDTO) {
        cardService.reloadCard(reloadCardDTO);
    }

}
