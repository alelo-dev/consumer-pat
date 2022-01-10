package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.*;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import br.com.alelo.consumer.consumerpat.util.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/consumers")
@Validated
@RequiredArgsConstructor
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ExtractRepository extractRepository;

    private final CardService cardService;
    private final ExtractService extractService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Consumer> listAllConsumers(@RequestParam("page") int page,
                                           @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return consumerService.listAll(pageable);
    }

    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                                                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createConsumer(@Valid @RequestBody ConsumerCreateReq consumer){
        Consumer saved = null;
        try {
            saved = consumerService.saveConsumer(consumer);
        } catch (Exception e) {
            if(e instanceof CardIsExistException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            if(e instanceof Exception)
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().body(saved);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(path = "/{idConsumer}", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateConsumer(@PathVariable("idConsumer") String idConsumer,
                                         @Valid @RequestBody ConsumerUpdateReq consumer) {
        return consumerRepository.findById(idConsumer)
                .map(consu -> {
                    Consumer updated = consumerRepository.save(consumer.updateConsumer(consu));
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/set-card-balance/{cardNumber}/{value}", method = RequestMethod.POST)
    public ResponseEntity setBalance(@PathVariable("cardNumber") Long cardNumber, @PathVariable("value") BigDecimal value) {
        if(cardService.debitBalance(cardNumber, value)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buy(@Valid @RequestBody BuyRequest buyRequest) {
        try {
            extractService.buy(buyRequest.getCard(), buyRequest.getIdEstablishment(),
                                        buyRequest.getProductDescription(), buyRequest.getValue());
        } catch (Exception e) {
            if(e instanceof EstablishmentNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            if(e instanceof CardNotFoundException)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            if(e instanceof BalanceException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            if(e instanceof CardNotTypeException)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            if(e instanceof Exception)
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

}
