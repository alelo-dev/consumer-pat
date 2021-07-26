package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.business.object.ConsumerCardChargeBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerCreationBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerRecoveryBObject;
import br.com.alelo.consumer.consumerpat.business.object.ConsumerUpdateBObject;
import br.com.alelo.consumer.consumerpat.business.object.ExtractBObject;
import br.com.alelo.consumer.consumerpat.model.dto.ApiDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerAleloCardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.page.PageDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerRecoveryBObject consumerRecoveryBObject;
    private final ConsumerCreationBObject consumerCreationBObject;
    private final ConsumerUpdateBObject consumerUpdateBObject;
    private final ConsumerCardChargeBObject consumerCardChargeBObject;
    private final ExtractBObject extractBObject;

    public ConsumerController(ConsumerRecoveryBObject consumerRecoveryBObject,
                              ConsumerCreationBObject consumerCreationBObject,
                              ConsumerUpdateBObject consumerUpdateBObject,
                              ConsumerCardChargeBObject consumerCardChargeBObject,
                              ExtractBObject extractBObject) {
        this.consumerRecoveryBObject = consumerRecoveryBObject;
        this.consumerCreationBObject = consumerCreationBObject;
        this.consumerUpdateBObject = consumerUpdateBObject;
        this.consumerCardChargeBObject = consumerCardChargeBObject;
        this.extractBObject = extractBObject;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ApiOperation(value = "Este recurso retorna uma lista de clientes paginada")
    @GetMapping
    public ResponseEntity<ApiDTO<PageDTO<ConsumerDTO>>> listAllConsumers(@RequestParam(value = "page", required = false) Integer page,
                                                                         @RequestParam(value = "records", required = false) Integer records,
                                                                         @RequestHeader(name = "token", required = false) String token) {
        return this.consumerRecoveryBObject.prepareToRecoverAllCustomers(page, records);
    }


    /* Cadastrar novos clientes */
    @ApiOperation(value = "Este recurso grava um novo cliente em nossa base de dados")
    @PostMapping
    public ResponseEntity<ApiDTO<ConsumerDTO>> createConsumer(@RequestBody ConsumerDTO consumer,
                                                              @RequestHeader(name = "token", required = false) String token) {
        return this.consumerCreationBObject.prepareToSaveCustomer(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ApiOperation(value = "Este recurso atualiza um cliente existente em nossa base de dados")
    @PutMapping
    public ResponseEntity<ApiDTO<ConsumerDTO>> updateConsumer(@RequestBody ConsumerDTO consumer,
                                                              @RequestHeader(name = "token", required = false) String token) {
        return this.consumerUpdateBObject.prepareToUpdateCustomer(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ApiOperation(value = "Este recurso adiciona uma carga a um determinado cartão")
    @PutMapping(path = "/card/charge")
    public ResponseEntity<ApiDTO<ConsumerAleloCardDTO>> setBalance(@RequestHeader(name = "cardNumber") String cardNumber,
                                                                   @RequestHeader(name = "amount") BigDecimal amount,
                                                                   @RequestHeader(name = "token", required = false) String token) {
        return this.consumerCardChargeBObject.prepareToChargeCard(cardNumber, amount);
    }

    @ApiOperation(value = "Este recurso realiza uma compra em um cartão")
    @PostMapping(path = "/buy")
    public ResponseEntity<ApiDTO<ExtractDTO>> buy(@RequestHeader(name = "establishmentType") Integer establishmentType,
                                                  @RequestHeader(name = "establishmentName") String establishmentName,
                                                  @RequestHeader(name = "cardNumber") String cardNumber,
                                                  @RequestHeader(name = "productDescription") String productDescription,
                                                  @RequestHeader(name = "value") BigDecimal value,
                                                  @RequestHeader(name = "token", required = false) String token) {
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
        *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
        *
        * Tipos de estabelcimentos
        * 1 - Alimentação (food)
        * 2 - Farmácia (DrugStore)
        * 3 - Posto de combustivel (Fuel)
        */
        return this.extractBObject.prepareToExtract(establishmentType, establishmentName, cardNumber, productDescription, value);
    }

}
