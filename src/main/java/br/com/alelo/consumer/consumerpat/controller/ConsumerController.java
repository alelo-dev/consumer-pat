package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.mapper.ConsumerDtoMapper;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Controlador responsável por tratar requisições de gerenciamento de dados de clientes.
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    private ConsumerService consumerService;

    private ExtractService extractService;

    private ConsumerRepository consumerRepository;

    private ExtractRepository extractRepository;

    private ConsumerDtoMapper consumerDtoMapper;

    @Autowired
    public ConsumerController(ConsumerService consumerService, ExtractService extractService, ConsumerDtoMapper consumerDtoMapper) {
        this.consumerService = consumerService;
        this.extractService = extractService;
        this.consumerRepository = this.consumerService.getConsumerRepository();
        this.extractRepository = this.extractService.getExtractRepository();
        this.consumerDtoMapper = consumerDtoMapper;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping
    public ResponseEntity<?> getAllConsumers() {
        try {
            List<Consumer> consumers = consumerService.findAll();
            return ResponseEntity.ok(consumerDtoMapper.toDtos(consumers));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable Long id) {
        try {
            Consumer consumer = consumerService.findById(id);
            return ResponseEntity.ok(consumerDtoMapper.toDto(consumer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<?> createConsumer(@RequestBody Consumer consumer) {

        try {
            consumerService.save(consumer);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(consumer.getId())
                    .toUri();

            return ResponseEntity.created(location).body(consumerDtoMapper.toDto(consumer));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateConsumerTotally(@RequestBody Consumer consumer, @PathVariable Long id) {
        return updateConsumer(consumer, id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateConsumerPartially(@RequestBody Consumer consumer, @PathVariable Long id) {
        return updateConsumer(consumer, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {

        // Embora sistemas em ambientes de produção não excluam registros eu
        // implementei esse método apenas para demonstrar o conhecimento.

        try {
            consumerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreCardNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                consumerRepository.save(consumer);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value) {
        Consumer consumer = null;
        /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
         *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
         *
         * Tipos de estabelcimentos
         * 1 - Alimentação (food)
         * 2 - Farmácia (DrugStore)
         * 3 - Posto de combustivel (Fuel)
         */

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (value / 100) * 10;
            value = value - cashback;

            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            consumerRepository.save(consumer);

        } else if (establishmentType == 2) {
            consumer = consumerRepository.findByDrugstoreCardNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            consumerRepository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (value / 100) * 35;
            value = value + tax;

            consumer = consumerRepository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            consumerRepository.save(consumer);
        }

        Extract extract = Extract.builder()
                .establishmentName(establishmentName)
                .productDescription(productDescription)
                .dateBuy(new Date())
                .cardNumber(cardNumber)
                .value(value)
                .build();

        extractRepository.save(extract);
    }

    private ResponseEntity<?> updateConsumer(Consumer consumer, Long id) {
        try {
            consumerService.save(consumer, id);
            return ResponseEntity.ok(consumerDtoMapper.toDto(consumer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
