package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardCreateResponseDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService service;
    private final ShoppingService shoppingService;

    @Autowired
    public CardController(CardService cardService, ShoppingService shoppingService) {

        this.service = cardService;
        this.shoppingService = shoppingService;
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping("/setcardbalance")
    public void setBalance(int cardNumber, BigDecimal value) {
        service.credit(cardNumber, value);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping
    public ResponseEntity<CardCreateResponseDTO> save(@RequestBody @Validated CardDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }




    @PostMapping("/buy")
    public ResponseEntity<TransactionDTO> buy(@RequestBody @Validated BuyDTO buyDTO) throws Exception {

        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */
        return ResponseEntity.ok(this.shoppingService.buy(buyDTO));

    }

}
