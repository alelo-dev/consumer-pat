package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/consumer", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@SuppressWarnings("squid:S4684")
public class ConsumerController {

    private ConsumerService consumerService;

    @ResponseBody
    @ResponseStatus(code = OK)
    @GetMapping(value = "/list-all-consumers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao listar os clientes")
    })
    public List<Consumer> listAllConsumers() {
        Pageable wholePage = Pageable.unpaged();
        return consumerService.listAllConsumers(wholePage);
    }

    @ResponseStatus(code = CREATED)
    @PostMapping(value = "/create-consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o cliente")
    })
    public ResponseEntity<String> createConsumer(@RequestBody Consumer consumer) {
        consumerService.save(consumer);
        return new ResponseEntity<>("Cliente cadastrado com sucesso", CREATED);
    }

    @ResponseStatus(code = OK)
    @PutMapping(value = "/update-consumer/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar os dados do cliente"),
            @ApiResponse(responseCode = "403", description = "Proibida a atualização do valor do cartão(ões)")
    })
    public ResponseEntity<String> updateConsumer(@PathVariable Long id, @RequestBody Consumer consumer) {
        if(id.equals(consumer.getId())) {
            consumerService.update(consumer);
            return new ResponseEntity<>("Dados atualizados com sucesso", OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
