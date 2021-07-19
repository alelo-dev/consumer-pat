package br.com.alelo.consumer.consumerpat.controller.contracts;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerCreateDto;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = {"Clientes"}, value = "Recursos de Clientes", hidden = true, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public interface ConsumerControllerContract {

    @ApiOperation(value = "Deve listar todos os clientes (cerca de 500)", notes = "Deve listar todos os clientes (cerca de 500)", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @GetMapping("/consumerList")
    ResponseEntity<Iterable<Consumer>> listAllConsumers(
            @PageableDefault(page = 0, value = 500, size = 500, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    );

    @ApiOperation(value = "Cadastrar novos clientes", notes = "Cadastrar novos clientes", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PostMapping("/createConsumer")
    ResponseEntity<Consumer> createConsumer(@RequestBody ConsumerCreateDto consumerCreateDto);

    @ApiOperation(value = "Atualizar clientes", notes = "Não deve ser possível alterar o saldo do cartão", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PutMapping("/updateConsumer/{consumerId}")
    ResponseEntity<Void> updateConsumer(@PathVariable Long consumerId, @RequestBody ConsumerUpdateDto consumerUpdateDto);
}
