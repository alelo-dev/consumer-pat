package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PatchMapping("/increaseBalance")
    public void increaseBalance(@RequestParam(required = true) Long cardNumber, @RequestParam(required = true) BigDecimal value) {
        transactionService.increaseBalance(cardNumber, value);
    }

    @GetMapping(value = "/buy")
    public void buy(Integer establishmentType, String establishmentName, Long cardNumber, String productDescription, BigDecimal value) {
        transactionService.buy(establishmentType, establishmentName, cardNumber, productDescription, value);
    }
}
