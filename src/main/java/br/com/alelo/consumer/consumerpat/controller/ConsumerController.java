package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ConsumerRepository consumerRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> listAllConsumers() {

        return ResponseEntity.ok().body(consumerService.getAllConsumersList());
    }

    /* Lista clientes por ID */
    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Consumer consumer = consumerService.findConsumerById(id);

        return ResponseEntity.ok().body(consumer);
    }

    /* Lista todos com parêmtros opcionais (ex: quebra por página) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Consumer>> listAllConsumersPageable(
            @RequestParam (value="page", defaultValue = "0") Integer page,
            @RequestParam (value="linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam (value="orderBy", defaultValue = "name") String orderBy,
            @RequestParam (value="direction", defaultValue = "ASC") String direction) {

        return ResponseEntity.ok().body(consumerService.findPage(page, linesPerPage, orderBy, direction));
    }

    /* Cadastrar novos clientes */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Void> newConsumer(@RequestBody Consumer consumer) {
        consumer = consumerService.createConsumer(consumer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(consumer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Consumer> updateConsumer(@RequestBody ConsumerDTO consumerDTO,
                                                   @PathVariable Integer id) {
        Consumer consumer = consumerService.fromDTO(consumerDTO);
        consumer.setId(id);

        consumerService.update(consumer);

        return ResponseEntity.ok().body(consumer);
    }


//  Encontra Consumer pelo No do Cartão.
    @RequestMapping(value = "/card/{cardNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> findByCard (@PathVariable Long cardNumber) {

        Consumer consumer = consumerService.findConsumerByCard(cardNumber);

        return ResponseEntity.ok().body(consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance/{cardNumber}", method = RequestMethod.PATCH)
    public ResponseEntity<?> setBalance (@PathVariable Long cardNumber,
                                         @RequestParam double value) {

        Consumer consumer = consumerService.addCreditValue(cardNumber, value);

        return ResponseEntity.ok().body(consumer);
    }
}
