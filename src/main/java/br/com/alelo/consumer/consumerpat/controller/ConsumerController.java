package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.domain.dto.ConsumerResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.dto.ExtractResponseDTO;
import br.com.alelo.consumer.consumerpat.domain.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
@Api
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;


    @ApiOperation(
            value="Lista de Consumidores - Consumer Pat",
            notes="Mostra Listagem de Consumidores.",
            response= ConsumerResponseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message = "",
                    response = ConsumerResponseDTO.class
            ),
            @ApiResponse(
                    code=404,
                    message="Mensagens de validação caso algum campo informado no JSON não seja encontrado na base de dados."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @GetMapping(value = "/consumerList")
    public ResponseEntity<List<ConsumerResponseDTO>> listAllConsumers() {
        return ResponseEntity.ok(consumerService.getAllConsumersList());
    }


    @ApiOperation(
            value="Cadastro de Consumidores - Consumer Pat",
            notes="Efetua o cadastro do Consumidor.",
            response= ConsumerResponseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=201,
                    message = "Cadastro de consumidor realizado com sucesso.",
                    response = ConsumerResponseDTO.class
            ),
            @ApiResponse(
                    code=404,
                    message="Mensagens de validação caso algum campo informado no JSON não seja encontrado na base de dados."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/createConsumer")
    public ResponseEntity<ConsumerResponseDTO> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consumerService.saveConsumer(consumer));
    }

    @ApiOperation(
            value="Atualiza cadastro dos Consumidores - Consumer Pat",
            notes="Efetua a atualização do cadastro do Consumidor.",
            response= ConsumerResponseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message = "Consumidor atualizado com sucesso.",
                    response = ConsumerResponseDTO.class
            ),
            @ApiResponse(
                    code=404,
                    message="Mensagens de validação caso algum campo informado no JSON não seja encontrado na base de dados."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PutMapping(value = "/updateConsumer")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok().body(consumerService.saveConsumer(consumer));
    }


    @ApiOperation(
            value="Adiciona valor a um cartão  - Consumer Pat",
            notes="Efetua adição de valor a um cartão específico de um consumidor específico.",
            response= ConsumerResponseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message = "Valor adicionado com sucesso.",
                    response = ConsumerResponseDTO.class
            ),
            @ApiResponse(
                    code=404,
                    message="Mensagens de validação caso algum campo informado no JSON não seja encontrado na base de dados."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PutMapping(value = "/setcardbalance")
    public ResponseEntity<ConsumerResponseDTO> setBalance(Integer cardNumber, Double value) {
        return ResponseEntity.ok().body(consumerService.setCardBalence(cardNumber,value));
    }

    @ApiOperation(
            value="Realizar Compra - Consumer Pat",
            notes="Efetua compra de um consumidor específico e com cartão específico, no valor informado.",
            response= ExtractResponseDTO.class)
    @ApiResponses(value= {
            @ApiResponse(
                    code=200,
                    message = "Compra realizada com sucesso.",
                    response = ExtractResponseDTO.class
            ),
            @ApiResponse(
                    code=404,
                    message="Mensagens de validação caso algum campo informado no JSON não seja encontrado na base de dados."
            ),
            @ApiResponse(
                    code=500,
                    message ="Ocorreu um erro inesperado. Contate o suporte!"
            )
    })
    @PostMapping(value = "/buy")
    public ResponseEntity<ExtractResponseDTO> buy(Integer establishmentType, String establishmentName,
                                                  Integer cardNumber, String productDescription, Double value) {
        return ResponseEntity.ok().body(consumerService.buy(establishmentType, establishmentName,  cardNumber,  productDescription,  value));
    }

}
