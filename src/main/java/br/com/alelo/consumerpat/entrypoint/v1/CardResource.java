package br.com.alelo.consumerpat.entrypoint.v1;

import br.com.alelo.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.request.CardRechargeV1RequestDto;
import br.com.alelo.consumerpat.core.exception.BadRequestException;
import br.com.alelo.consumerpat.core.exception.CardNotFoundException;
import br.com.alelo.consumerpat.core.exception.InvalidBalanceException;
import br.com.alelo.consumerpat.core.exception.InvalidEstablishmentForCardException;
import br.com.alelo.consumerpat.core.usecase.BuyUseCase;
import br.com.alelo.consumerpat.core.usecase.CardRechargeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/v1/cards")
public class CardResource {

    @Autowired
    private CardRechargeUseCase rechargeUseCase;

    @Autowired
    private BuyUseCase buyUseCase;

    @PutMapping("/{cardNumber}/recharge")
    @ApiOperation(value = "Realizar a recarga do cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })
    public ResponseEntity<Void> recharge(@PathVariable("cardNumber") String cardNumber, @RequestBody CardRechargeV1RequestDto request) throws BadRequestException {
        try {
            this.rechargeUseCase.recharge(cardNumber, request);

            return ResponseEntity.noContent().build();
        } catch (CardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cardNumber}/buys")
    @ApiOperation(value = "Realizar o débito da compra")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Server Internal Error")
    })
    public ResponseEntity<Void> buys(@PathVariable("cardNumber") String cardNumber, @RequestBody CardBuyV1RequestDto request)
            throws InvalidBalanceException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidEstablishmentForCardException, CardNotFoundException {
        try {
            this.buyUseCase.calculateBalance(cardNumber, request);

            return ResponseEntity.noContent().build();
        } catch (CardNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
