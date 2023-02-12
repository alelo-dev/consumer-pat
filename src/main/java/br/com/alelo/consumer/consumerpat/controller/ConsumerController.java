package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.ServiceWarningException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.card.CardTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Log4j2
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private Mapper mapper;

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    //@ResponseBody
    @Operation(summary = "Return a paged list of consumers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Return a paged list of consumers"),
        @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
        @ApiResponse(responseCode = "401", description = "User invalid", content = @Content),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<PageData<Consumer>> listAllConsumers(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "50") int size) {
        log.info("listAllConsumers: Getting paged consumers");

        Pageable paging = PageRequest.of(page, size);
        PageData<Consumer> pageConsumers = consumerService.getAllConsumersListByPage(paging);

        log.info("listAllConsumers: Returning paged consumers");

        return new ResponseEntity<>(pageConsumers, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @Operation(summary = "Create a new consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created consumer"),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content)
    })
    @PostMapping(value = "")
    public void createConsumer(@RequestBody @Valid ConsumerCreateDto consumerDto) {

        log.info("createConsumer - Enter");

        Consumer consumer = mapper.toConsumer(consumerDto);
        consumerService.saveConsumer(consumer);

        log.info("createConsumer - Created");

    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão
    @Operation(summary = "Update a consumer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated consumer"),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Consumer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content),
    })
    @PutMapping(value = "/{id}")
    public void updateConsumer(@RequestBody @Valid ConsumerUpdateDto consumerDto, @NotBlank @PathVariable String id) {

        log.info("updateConsumer - Enter");

        Consumer consumer = mapper.toConsumer(consumerDto);
        consumer.setIdentifier(id);
        consumerService.saveConsumer(consumer);

        log.info("updateConsumer - Updated");
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @Operation(summary = "Credit value to a specific card number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credited"),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Consumer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content)
    })
    @PostMapping(value = "/credit")
    public void credit(@RequestBody @Valid ConsumerCreditDto consumerCreditDto) {

        log.info("credit - Updating credit");

        consumerService.credit(consumerCreditDto.getCardNumber(), consumerCreditDto.getValue());

        log.info("credit - Credit updated");
    }

    /*
     * Débito de valor no cartão (compra)
     *
     * establishmentType: tipo do estabelecimento comercial
     * establishmentName: nome do estabelecimento comercial
     * cardNumber: número do cartão
     * productDescription: descrição do produto
     * value: valor a ser debitado (subtraído)
     */
    @Operation(summary = "Debit value from a specific card number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Debited"),
            @ApiResponse(responseCode = "400", description = "Invalid data/Insufficient Balance", content = @Content),
            @ApiResponse(responseCode = "401", description = "User invalid", content = @Content),
            @ApiResponse(responseCode = "404", description = "Consumer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content)
    })
    @PostMapping(value = "/debit")
    public void debit(@RequestBody @Valid ConsumerDebitDto consumerDebitDto) {

        log.info("debit - Creating debit");

        CardTypeEnum establishmentType = null;
        try {
            establishmentType = CardTypeEnum.values()[consumerDebitDto.getEstablishmentType()-1];
        }catch (IndexOutOfBoundsException e) {
            log.warn("Establishment type invalid: " + consumerDebitDto.getEstablishmentType());
            throw new ServiceWarningException("Establishment type invalid");
        }

        consumerService.debit(establishmentType, consumerDebitDto.getEstablishmentNameId(),
                consumerDebitDto.getEstablishmentName(), consumerDebitDto.getCardNumber(),
                consumerDebitDto.getProductDescription(), consumerDebitDto.getValue());

        log.info("debit - Debit created");

    }

}