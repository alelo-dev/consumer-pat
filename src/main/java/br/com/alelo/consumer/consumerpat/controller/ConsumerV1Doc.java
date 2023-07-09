package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerUpdateDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@Tag(name = "Consumer", description = "API de gerenciamento de consumidores")
interface ConsumerV1Doc {

    @Operation(summary = "Obter consumidores de forma paginada")
    @ApiResponse(responseCode = "200", description = "Consumidores encontrados e paginados")
    ResponseEntity<Page<Consumer>> getAllConsumers(@Parameter(description = "Paginação") Pageable pageable);

    @Operation(summary = "Criar novo consumidor")
    @ApiResponse(responseCode = "201", description = "Consumidor criado com sucesso")
    @ApiResponse(responseCode = "500", description = "Falha ao criar consumidor")
    ResponseEntity<?> createConsumer(@Parameter(description = "Consunidor para criação", required = true) ConsumerDTO consumerDTO);

    @Operation(summary = "Atualizar consumidor")
    @ApiResponse(responseCode = "200", description = "Consumidor atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Consumidor não encontrado")
    @ApiResponse(responseCode = "500", description = "Falha ao atualizar consumidor")
    ResponseEntity<?> updateConsumer(@Parameter(description = "Id consumidor", required = true) Long id,
                                     @Parameter(description = "Consumidor para atualização", required = true) ConsumerUpdateDTO consumerDTO);

    @Operation(summary = "Creditar valor no cartão")
    @ApiResponse(responseCode = "200", description = "Balanço atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Falha ao atualizar balanço")
    ResponseEntity<?> creditCardBalance(
            @Parameter(description = "Número do cartão", required = true) Long cardNumber,
            @Parameter(description = "Valor para creditar", required = true) Double value);

    @Operation(summary = "Realizar transação")
    @ApiResponse(responseCode = "200", description = "Transação atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Cartão ou tipo do estabelecimento não encontrado")
    @ApiResponse(responseCode = "500", description = "Falha ao realizar transação")
    ResponseEntity<?> makeTransaction(
            @Parameter(description = "Número do cartão", required = true) Long cardNumber,
            @Parameter(description = "Tipo do estabelecimento", required = true) Integer establishmentType,
            @Parameter(description = "Nome estabelecimento", required = true) String establishmentName,
            @Parameter(description = "Descrição do produto", required = true) String productDescription,
            @Parameter(description = "Valor transação", required = true) Double value);

}
