package br.com.alelo.consumer.consumerpat.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.api.dto.CurrentBalanceValue;
import br.com.alelo.consumer.consumerpat.api.dto.input.CreditInput;
import br.com.alelo.consumer.consumerpat.api.dto.input.PaymentInput;
import br.com.alelo.consumer.consumerpat.api.mapper.PaymentMapper;
import br.com.alelo.consumer.consumerpat.domain.model.dto.PaymentInformation;
import br.com.alelo.consumer.consumerpat.domain.service.ManageBalanceTransactionsService;

@RestController
@RequestMapping("api/cards")
public class CardController {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private ManageBalanceTransactionsService manageBalanceService;

    @PutMapping("/credit")
    public CurrentBalanceValue addCredit(@RequestBody CreditInput input) {
        BigDecimal newBalanceValue = manageBalanceService.addCreditToCard(input.getCardNumber(), input.getValue());
        return new CurrentBalanceValue(newBalanceValue);
    }

    @PutMapping("/payment")
    public CurrentBalanceValue pay(@RequestBody PaymentInput input) {
        PaymentInformation paymentInformation = paymentMapper.toDomainObject(input);
        BigDecimal newBalanceValue = manageBalanceService.makePayment(paymentInformation);
        return new CurrentBalanceValue(newBalanceValue);
    }
}
