package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.response.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.mapper.ConsumerMapper;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/consumer")
@RequiredArgsConstructor
@Api(value = "/consumer", description = "Operations about consumers")
public class ConsumerController {

    private final ConsumerService consumerService;
    private final ConsumerMapper consumerMapper;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK, reason = "List sucessful")
    @GetMapping(value = "/consumerList")
    @ApiOperation(value = "List all consumers", response = ConsumerDto.class)
    public List<ConsumerDto> listAllConsumers() {
        List<Consumer> allConsumers = consumerService.getAllConsumersList();
        return allConsumers.stream()
                            .map(consumer -> consumerMapper.toDto(consumer))
                            .collect(Collectors.toList());
    }


    /* Cadastrar novos clientes */
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Create sucessful")
    @PostMapping(value = "/createConsumer")
    @ApiOperation(value = "Update customer card informations", response = ConsumerDto.class)
    public ResponseEntity<ConsumerDto> createConsumer(@RequestBody ConsumerDto consumerDto) {
        Consumer updatedConsumer = consumerService.save(consumerMapper.toEntity(consumerDto));
        return ResponseEntity.ok(consumerMapper.toDto(updatedConsumer));
    }

}
