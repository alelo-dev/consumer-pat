package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.dto.BalanceDTO;
import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /**
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     * 
     * @param cardNumber
     * @param value
     */
    @PostMapping(value = "/{cardNumber}/balance/set")
    public ResponseEntity<HttpStatus> setBalance(@PathVariable("cardNumber") String cardNumber, @RequestBody BalanceDTO balanceDTO) {
        //TODO Validar dados de entrada

        Optional<CardDTO> optional = cardService.setBalance(cardNumber, balanceDTO.getValue());
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return  ResponseEntity.ok().build();
    }

     /**
     * O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     * 
     * @param cardNumber
     * @param buyDTO
     */
    @PostMapping(value = "/{cardNumber}/buy")
    public ResponseEntity<?> registerBuy(@PathVariable("cardNumber") String cardNumber, @RequestBody BuyDTO buyDTO) {
        //TODO Validar as informações de entrada

        Optional<BigDecimal> optional = cardService.registerBuy(cardNumber, buyDTO);
        if (optional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new BalanceDTO(optional.get()));
    }

}
