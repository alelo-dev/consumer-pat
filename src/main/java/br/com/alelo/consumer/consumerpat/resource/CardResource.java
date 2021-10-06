package br.com.alelo.consumer.consumerpat.resource;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cards")
public interface CardResource {

    String RETORNA_SUCESSO       = "Retorna sucesso";
    String OBJETO_NAO_ENCONTRADO = "Foi uma exceção de objeto não encontrado";
    String FOI_UM_ERRO_INTERNO   = "Foi gerado um erro interno";

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/setCardbalance")
    ResponseEntity<Card> setBalance(@RequestParam String cardNumber, @RequestParam Double value);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RETORNA_SUCESSO),
            @ApiResponse(code = 404, message = OBJETO_NAO_ENCONTRADO),
            @ApiResponse(code = 500, message = FOI_UM_ERRO_INTERNO)
    })
    @PostMapping(value = "/buy")
    ResponseEntity<Card> buy(@RequestBody Purchase obj);
}
