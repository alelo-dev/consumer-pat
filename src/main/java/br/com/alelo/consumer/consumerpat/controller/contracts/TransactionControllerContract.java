package br.com.alelo.consumer.consumerpat.controller.contracts;

import br.com.alelo.consumer.consumerpat.entity.dto.BuyCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardBalanceDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Transacoes"}, value = "Recursos de Transacoes", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface TransactionControllerContract {

    @ApiOperation(value = "Deve creditar(adicionar) um valor(value) em um no cartão.", notes = "Deve creditar(adicionar) um valor(value) em um no cartão.", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping(value = "/setcardbalance")
    ResponseEntity<Map<String, BigDecimal>> setBalance(@RequestBody CardBalanceDTO cardBalanceDTO);

    @ApiOperation(value = "Deve creditar(debitar) um valor(value) em um no cartão.",
            notes = "O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.\n" +
                    "     *  Exemplo: Se a compra é em um estabelecimento de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação\n" +
                    "     *\n" +
                    "     * Tipos de estabelecimentos\n" +
                    "     * 1 - Alimentação (food)\n" +
                    "     * 2 - Farmácia (DrugStore)\n" +
                    "     * 3 - Posto de combustivel (Fuel)",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping(value = "/buy")
    ResponseEntity<Void> buy(@RequestBody BuyCardBalanceDTO cardBalanceDTO);
}
