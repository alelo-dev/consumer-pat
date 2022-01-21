package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.converter.ConsumerConverter;
import br.com.alelo.consumer.consumerpat.controller.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.controller.validator.ConsumerValidator;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/consumerList")
    public List<Consumer> listAllConsumers() {
        return repository.getAllConsumersList();
    }

    /**
     * Cadastrar novos clientes
     * @param createConsumer - Informações do cliente
     */
    @PostMapping
    public ResponseEntity<String> createConsumer(@RequestBody CreateConsumerDTO createConsumer) {
        final String validated = ConsumerValidator.validate(createConsumer);
        try {
            if (Objects.isNull(validated)) {
                consumerService.createConsumer(ConsumerConverter.toEntity(createConsumer));
                return ResponseEntity.ok().build();
            } else {
                log.error("Falha validação");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validated);
            }
        } catch (Exception e) {
            log.error("Falha geral {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Atualiza dados do cliente
     * Não deve ser possível alterar o saldo do cartão
     * @param id - id do cliente
     */
    @PatchMapping(value = "/updateConsumer/{id}")
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody UpdateConsumerDTO updateConsumerDTO) {
        consumerService.updateConsumer(id);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */

/*
    @GetMapping(value = "/setcardbalance")
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if (consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            repository.save(consumer);
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if (consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                repository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                repository.save(consumer);
            }
        }
    }
*/


    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */

  /*
    @ResponseBody
    @GetMapping(value = "/buy")
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        Consumer consumer = null;

        if (establishmentType == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        } else if (establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
    }
    */
}
