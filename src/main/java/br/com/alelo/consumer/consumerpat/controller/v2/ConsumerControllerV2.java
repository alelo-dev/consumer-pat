package br.com.alelo.consumer.consumerpat.controller.v2;

import br.com.alelo.consumer.consumerpat.business.IConsumerBusiness;
import br.com.alelo.consumer.consumerpat.dto.v2.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.entity.ExtractEntity;
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
import java.util.Optional;

@RestController
@RequestMapping("/v2/consumer")
public class ConsumerControllerV2 {

    @Autowired
    IConsumerBusiness iConsumerBusiness;

    // @Autowired
    // ExtractRepository extractRepository;
    //Usaria uma solução reativa utilizando ,WebFlux com Reactor, retornando APPLICATION_JSON_STREAM_VALUE
    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar todos os clientes", code = 200)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<List<ConsumerDTO>> listAllConsumers() {

        Optional<List<ConsumerDTO>> dtos = iConsumerBusiness.listAllConsumers();

        if (dtos.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(dtos.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

    }

    /* Cadastrar novos clientes */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Criar Cliente.", code = 201)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cliente criado com sucesso!"),
        @ApiResponse(code = 400, message = "Parâmetros Inválidos!"),
        @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    })
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody final ConsumerDTO consumer) {
        return ResponseEntity.status(HttpStatus.OK).body(iConsumerBusiness.create(consumer).get());
    }

    // // Não deve ser possível alterar o saldo do cartão
    // @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // @ApiResponses(value = {
    //     @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
    //     @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    // })
    // public ResponseEntity<ConsumerEntity> updateConsumer(@RequestBody ConsumerEntity consumer) {
    //     return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    // }

    // /*
    //  * Deve creditar(adicionar) um valor(value) em um no cartão.
    //  * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
    //  * para isso deve usar o número do cartão(cardNumber) fornecido.
    //  */
    // @PutMapping(path = "/cardbalance", produces = MediaType.APPLICATION_JSON_VALUE)
    // @ApiResponses(value = {
    //     @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
    //     @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    // })
    // public ResponseEntity<ConsumerEntity> updateBalance(int cardNumber, double value) {
    //     ConsumerEntity consumer = null;
    //     consumer = repository.findByDrugstoreNumber(cardNumber);

    //     if(consumer != null) {
    //         // é cartão de farmácia
    //         consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
    //         return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    //     } else {
    //         consumer = repository.findByFoodCardNumber(cardNumber);
    //         if(consumer != null) {
    //             // é cartão de refeição
    //             consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
    //             return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    //         } else {
    //             // É cartão de combustivel
    //             consumer = repository.findByFuelCardNumber(cardNumber);
    //             consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
    //             return ResponseEntity.status(HttpStatus.OK).body(repository.save(consumer));
    //         }
    //     }
    // }

    // @ResponseBody
    // @PostMapping(path = "/buy" ,produces = MediaType.APPLICATION_JSON_VALUE)
    // @ApiResponses(value = {
    //     @ApiResponse(code = 204, message = "Nenhum cliente encontrado!"),
    //     @ApiResponse(code = 500, message = "Erro não tratado pelo servidor.")
    // })
    // public ResponseEntity<ExtractEntity> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
    //     ConsumerEntity consumer = null;
    //     /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
    //     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
    //     *
    //     * Tipos de estabelcimentos
    //     * 1 - Alimentação (food)
    //     * 2 - Farmácia (DrugStore)
    //     * 3 - Posto de combustivel (Fuel)
    //     */

    //     if (establishmentType == 1) {
    //         // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
    //         Double cashback  = (value / 100) * 10;
    //         value = value - cashback;

    //         consumer = repository.findByFoodCardNumber(cardNumber);
    //         consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
    //         repository.save(consumer);

    //     }else if(establishmentType == 2) {
    //         consumer = repository.findByDrugstoreNumber(cardNumber);
    //         consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
    //         repository.save(consumer);

    //     } else {
    //         // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
    //         Double tax  = (value / 100) * 35;
    //         value = value + tax;

    //         consumer = repository.findByFuelCardNumber(cardNumber);
    //         consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
    //         repository.save(consumer);
    //     }

    //     ExtractEntity extract = new ExtractEntity(establishmentName, productDescription, new Date(), cardNumber, value);
    //     return ResponseEntity.status(HttpStatus.OK).body(extractRepository.save(extract));
    // }

}
