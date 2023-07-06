package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.BalanceModel;
import br.com.alelo.consumer.consumerpat.model.BuyModel;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/* Review: Alterando de @Controler para @RestController não existe necessidade de anotar cada método
separadamente para que apareça no swagger*/

@Log4j2
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    ConsumerService service;

    ExtractRepository extractRepository;

    /* Review: A implementação do construtor permite que, em um escopo de teste, você possa
        instanciar a classe sem usar um framework para injeção de dependencia
     */
    ConsumerController(ConsumerService service, ExtractRepository extractRepository){
        this.service = service;
        this.extractRepository = extractRepository;
    }

    /* Review: Removendo redudancia dos paths tal qual POST e /add */

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */

    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100") Integer itemsPerPage) {
        log.info("Getting all clients");

        Page<Consumer> response = service.getAllConsumers(page, itemsPerPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        log.info("Creating consumer");
        return new ResponseEntity<>(service.save(consumer), HttpStatus.CREATED);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão

    @PatchMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        log.info("Updating consumer");
        return new ResponseEntity<>(service.update(consumer), HttpStatus.OK);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @PatchMapping(value = "card/balance")
    public ResponseEntity<Consumer> addBalance(@RequestBody @Valid  BalanceModel balance) {
        log.info("Adding balance to card: "+ balance.getCardNumber());

        Consumer response = service.addValue(balance.getCardNumber(), balance.getValue());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
    @PostMapping(value = "/buy")
    public ResponseEntity<Consumer> buy(@RequestBody @Valid BuyModel buyModel) {
        log.info("Buying product for card: " + buyModel.getCardNumber());

        return new ResponseEntity<>(service.buy(buyModel), HttpStatus.OK);
    }

}
