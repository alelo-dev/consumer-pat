package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class ConsumerController {

    ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    /*  */
    @Operation(summary = "Cadastrar novos clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Cadastrado")
    })
    @RequestMapping(value = "consumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.insert(consumer);
    }


    @Operation(summary = "Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @RequestMapping(value = "consumer", method = RequestMethod.PATCH)
    public void updateConsumer(@NonNull @RequestBody Consumer consumer) {
        consumerService.update(consumer);
    }

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consumer/list/paged", method = RequestMethod.GET)
    public Page<Consumer> list(@ParameterObject Pageable pageable) {
        log.info("obtendo todos clientes por pagina, {}", pageable);
        return consumerService.findAll(pageable);
    }

}
