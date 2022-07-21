package br.com.alelo.consumer.consumerpat.api.controller;

import br.com.alelo.consumer.consumerpat.api.dto.CardBalanceDto;
import br.com.alelo.consumer.consumerpat.api.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.api.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.api.dto.PurchaseDataDto;
import br.com.alelo.consumer.consumerpat.api.dto.mapper.ConsumerDtoMapper;
import br.com.alelo.consumer.consumerpat.api.dto.mapper.ExtractDtoMapper;
import br.com.alelo.consumer.consumerpat.api.dto.validation.ConsumerDtoValidator;
import br.com.alelo.consumer.consumerpat.domain.converter.BigDecimalConverter;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import br.com.alelo.consumer.consumerpat.domain.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.service.ExtractService;
import br.com.alelo.consumer.consumerpat.domain.util.BigDecimalCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

/**
 * Controlador responsável por tratar requisições de gerenciamento de dados de clientes.
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    // TODO 1 Executar testes básicos no cadastro
    // TODO 2 Executar testes básicos nas transações
    // TODO 3 Criar testes unitários para Repository
    // TODO 4 Criar testes unitários para Controller

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private ConsumerDtoMapper consumerDtoMapper;

    @Autowired
    private ExtractDtoMapper extractDtoMapper;

    @Autowired
    private ConsumerDtoValidator consumerDtoValidator;


    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public ResponseEntity<?> getAllConsumers() {
        List<Consumer> consumers = consumerService.findAllConsumers();
        return ResponseEntity.ok(consumerDtoMapper.toDtos(consumers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable BigInteger id) {
        Consumer consumer = consumerService.findConsumerById(id);
        return ResponseEntity.ok(consumerDtoMapper.toDto(consumer));
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<?> createConsumer(@RequestBody ConsumerDto consumerDto) {

        consumerDtoValidator.validate(consumerDto);

        Consumer consumer = consumerService.saveConsumer(consumerDtoMapper.toEntity(consumerDto));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consumerDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(consumerDtoMapper.toDto(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateConsumerTotally(@RequestBody ConsumerDto consumerDto, @PathVariable BigInteger id) {
        return updateConsumer(consumerDto, id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateConsumerPartially(@RequestBody ConsumerDto consumerDto, @PathVariable BigInteger id) {
        return updateConsumer(consumerDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroyConsumer(@PathVariable BigInteger id) {

        // OBSERVAÇÃO: Embora sistemas em ambientes de produção não excluam registros eu
        // implementei esse método apenas para demonstrar o conhecimento.
        consumerService.deleteConsumer(id);

        return ResponseEntity.noContent().build();
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping(value = "/setcardbalance")
    public ResponseEntity<?> setConsumerCardBalance(@RequestBody CardBalanceDto cardBalanceDto) {

        Consumer consumer = consumerService.setConsumersCardBalance(cardBalanceDto.getCardNumber(),
                cardBalanceDto.getValue(), cardBalanceDto.getCardType());

        return ResponseEntity.ok().body(consumerDtoMapper.toDto(consumer));
    }

    private ResponseEntity<?> updateConsumer(ConsumerDto consumerDto, BigInteger id) {

        ConsumerDtoValidator.validateIfNotNullDto(consumerDto);

        consumerDto.setId(id);

        ConsumerDtoValidator.validateId(consumerDto);

        Consumer consumer = consumerService.saveConsumer(consumerDtoMapper.toEntity(consumerDto));

        return ResponseEntity.ok(consumerDtoMapper.toDto(consumer));
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody PurchaseDataDto purchaseDataDto) {

        Consumer consumer;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        BigDecimal value = BigDecimalConverter.convertToBigDecimal(purchaseDataDto.getValue());
        purchaseDataDto.setValue(value);

        if (PurchaseDataDto.isFoodEstablishmentType(purchaseDataDto)) {

            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            BigDecimal cashback = BigDecimalCalculator.of(value)
                    .multiply(0.1)
                    .getResult();

            value = value.subtract(cashback);

            consumer = consumerService.findConsumerByCardNumber(purchaseDataDto.getCardNumber(), CardType.FOOD_CARD);
            BigDecimal foodCardBalance = consumer.getFoodCardBalance().subtract(value);
            consumer.setFoodCardBalance(foodCardBalance);

        } else if (PurchaseDataDto.isDrugstoreEstablishmentType(purchaseDataDto)) {

            consumer = consumerService.findConsumerByCardNumber(purchaseDataDto.getCardNumber(), CardType.DRUGSTORE_CARD);
            BigDecimal drugstoreCardBalance = consumer.getDrugstoreCardBalance().subtract(value);
            consumer.setDrugstoreCardBalance(drugstoreCardBalance);

        } else {

            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            BigDecimal tax = BigDecimalCalculator.of(value)
                    .divide(100.0)
                    .multiply(35.0)
                    .getResult();

            value = value.add(tax);

            consumer = consumerService.findConsumerByCardNumber(purchaseDataDto.getCardNumber(), CardType.FUEL_CARD);
            BigDecimal fuelCardBalance = consumer.getFuelCardBalance().subtract(value);
            consumer.setFuelCardBalance(fuelCardBalance);
        }

        consumerService.saveConsumer(consumer);

        ExtractDto extractDto = ExtractDto.convertToExtractDto(purchaseDataDto);
        extractService.saveExtract(extractDtoMapper.toEntity(extractDto));

        return ResponseEntity.ok(extractDto);
    }

}
