package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.contracts.TransactionControllerContract;
import br.com.alelo.consumer.consumerpat.entity.dto.BuyCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements TransactionControllerContract {

    @Autowired
    private TransactionService transactionService;

    public ResponseEntity<Map<String, BigDecimal>> setBalance(CardBalanceDTO cardBalanceDTO) {
        return ResponseEntity.ok().body(
                Map.of("newBalanceValue",
                        transactionService.addBalance(cardBalanceDTO))
        );
    }

    public ResponseEntity<Void> buy(BuyCardBalanceDTO cardBalanceDTO) {
        transactionService.processBuy(cardBalanceDTO);
        return ResponseEntity.ok().build();
    }
}
