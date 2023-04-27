package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.BalanceService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/balance")
public class BalanceController {

    private ConsumerService consumerService;
    private BalanceService balanceService;

    public BalanceController(ConsumerService consumerService, BalanceService balanceService) {
        this.consumerService = consumerService;
        this.balanceService = balanceService;
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @ResponseBody
    @RequestMapping(value = "/credit", method = RequestMethod.POST)
    public void setBalance(Long cardNumber, Double value) {
        this.balanceService.setCredit(cardNumber, value);
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(Integer establishmentType, String establishmentName, Long cardNumber, String productDescription, Double value) {
        this.balanceService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "extract/list/paged", method = RequestMethod.GET)
    public Page<Extract> list(@ParameterObject Pageable pageable) {
        log.info("obtendo todos registros de extrato por pagina, {}", pageable);
        return this.balanceService.findAllExtract(pageable);
    }

}
