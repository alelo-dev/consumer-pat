package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.request.PurchaseRequest;
import br.com.alelo.consumer.consumerpat.response.ExtractResponse;
import br.com.alelo.consumer.consumerpat.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Create a new buy ", notes = "Shopping with alelo card", httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 201, message = "Successful purchase", response = ExtractResponse.class),
                   @ApiResponse(code = 422, message = "Card not found /" +
                    "already contains a FOOD card linked to the consumer /" +
                    "No limit for purchase /" +
                    "Purchase denied, inactive card")})
    @PostMapping
    public ResponseEntity<ExtractResponse> purchase(@Validated @RequestBody PurchaseRequest purchaseRequest) {
        return new ResponseEntity<>(purchaseService.purchase(purchaseRequest), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get extract for purchase code", notes = "Get a specific extract for purchase code", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Extract found", response = ExtractResponse.class),
            @ApiResponse(code = 404, message = "Extract not found")
    })
    @GetMapping("/{purchaseCode}")
    public ResponseEntity<ExtractResponse> getByPurchaseCode(@PathVariable("purchaseCode") String purchaseCode) throws BusinessException {
        return ResponseEntity.ok(purchaseService.getByPurchaseCode(purchaseCode));
    }
}
