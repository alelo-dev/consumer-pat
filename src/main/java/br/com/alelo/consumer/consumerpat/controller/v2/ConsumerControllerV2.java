package br.com.alelo.consumer.consumerpat.controller.v2;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v2/consumer")
public class ConsumerControllerV2 {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deve Listar todos os clientes", code = 200)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<List<Consumer>> listAllConsumers() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.getAllConsumersList());
    }

    /* Cadastrar novos clientes */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deve Listar todos os clientes", code = 200)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PutMapping(path = "/cardbalance", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<Consumer> updateBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = repository.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
        } else {
            consumer = repository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
            } else {
                // É cartão de combustivel
                consumer = repository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
            }
        }
    }

    @ResponseBody
    @PostMapping(path = "/buy" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<Extract> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
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
            Double cashback  = (value / 100) * 10;
            value = value - cashback;

            consumer = repository.findByFoodCardNumber(cardNumber);
            consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
            repository.save(consumer);

        }else if(establishmentType == 2) {
            consumer = repository.findByDrugstoreNumber(cardNumber);
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
            repository.save(consumer);

        } else {
            // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
            Double tax  = (value / 100) * 35;
            value = value + tax;

            consumer = repository.findByFuelCardNumber(cardNumber);
            consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
            repository.save(consumer);
        }

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        return ResponseEntity.status(HttpStatus.OK).body(extractRepository.save(extract));
    }

}
