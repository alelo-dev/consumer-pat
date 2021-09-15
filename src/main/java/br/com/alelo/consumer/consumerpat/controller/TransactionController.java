package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.dto.response.TransactionDto;
import br.com.alelo.consumer.consumerpat.entity.Transaction;
import br.com.alelo.consumer.consumerpat.mapper.TransactionMapper;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Api(value = "/transaction", description = "Operations about card transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping(value = "/buy")
    @ResponseStatus(code = HttpStatus.OK, reason = "Purchase sucessfull")
    @ApiOperation(value = "Buy a product with a customer card a from establishment", response = TransactionDto.class)
    public ResponseEntity<TransactionDto> buy(@RequestBody PurchaseRequest purchaseRequest) {
        Transaction purchase = transactionService.buy(purchaseRequest);
        return ResponseEntity.ok(transactionMapper.toDto(purchase));
    }
}
