package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController extends BaseController {

    private final CardService service;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = "/increaseCardBalance")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void setBalance(@RequestBody IncreaseCardBalanceDTO dto) {
        service.increaseCardBalance(dto);
    }

    @PostMapping(value = "/buy")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void buy(@RequestBody TransactionDTO dto) {
        service.process(dto);
    }

}
