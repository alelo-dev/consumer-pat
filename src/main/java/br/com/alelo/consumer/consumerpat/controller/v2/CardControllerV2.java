package br.com.alelo.consumer.consumerpat.controller.v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.alelo.consumer.consumerpat.business.ICardControllerBusiness;
import br.com.alelo.consumer.consumerpat.dto.v2.CardDTO;
import br.com.alelo.consumer.consumerpat.enuns.CardTypeEnum;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/v2/card")
public class CardControllerV2 {

    @Autowired
    ICardControllerBusiness cardBusiness;

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão. Para isso ele
     * precisa indenficar qual o cartão correto a ser recarregado, para isso deve
     * usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(path = "/{number}/cardBalance/{value}/type/{cardType}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saldo do atualizado com sucesso!"),
            @ApiResponse(code = 204, message = "Nenhum cartão informado com os dados informados!"),
            @ApiResponse(code = 400, message = "Parâmetro de requisição inválido!"),
            @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.") })
    public ResponseEntity<CardDTO> updateConsumer(@PathVariable("number") Integer number,
            @PathVariable("value") BigDecimal value, @PathVariable("cardType") CardTypeEnum type) {

        Optional<CardDTO> dto = cardBusiness.updateBalance(number, value, type);

        if (dto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(dto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dto.get());
        }

    }

}
