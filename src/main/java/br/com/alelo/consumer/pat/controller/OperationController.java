package br.com.alelo.consumer.pat.controller;

import br.com.alelo.consumer.pat.domain.EstablishmentType;
import br.com.alelo.consumer.pat.service.RechargeService;
import br.com.alelo.consumer.pat.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationController {

    private final RechargeService rechargeService;
    private final TransactionService transactionService;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = "/recharge")
    public void rechargeCard(String cardNumber, BigDecimal value) {
        rechargeService.rechargeCard(cardNumber, value);
    }

    @PostMapping(value = "/buy")
    public void buy(EstablishmentType establishmentType, Long establishmentId, String establishmentName, String cardNumber, String productDescription, BigDecimal value) {
        transactionService.buyProduct(establishmentType, establishmentId, establishmentName, cardNumber, productDescription, value);
    }

}
