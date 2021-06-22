package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.parameter.PurchaseParameter;
import br.com.alelo.consumer.consumerpat.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;

@AllArgsConstructor
@RestController
@RequestMapping("purchases")
public class PurchaseController {

    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Void> postPurchase(@RequestBody PurchaseParameter parameter) {
        purchaseService.buy(parameter);
        return created(null).build();
    }
}
