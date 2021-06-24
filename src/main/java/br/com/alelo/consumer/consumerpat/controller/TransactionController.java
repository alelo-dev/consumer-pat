package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.parameter.TransactionParameter;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import static org.springframework.http.ResponseEntity.created;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("transactions")
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping
    @ApiOperation("Transação de compra")
    public ResponseEntity<Void> postTransaction(@RequestBody TransactionParameter parameter) {
        transactionService.buy(parameter);
        return created(null).build();
    }
}