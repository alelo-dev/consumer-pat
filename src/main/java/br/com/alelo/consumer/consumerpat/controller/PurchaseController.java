package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.parameter.PurchaseParameter;
import br.com.alelo.consumer.consumerpat.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Compras", description = "Chamadas para compras")
public class PurchaseController {

    private PurchaseService purchaseService;

    @PostMapping
    @ApiOperation("Registro de compra")
    public ResponseEntity<Void> postPurchase(@RequestBody PurchaseParameter parameter) {
        purchaseService.buy(parameter);
        return created(null).build();
    }
}
