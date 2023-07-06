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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
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

    /* Review: Removendo redudancia dos paths */

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */

    @ResponseBody
    @GetMapping
    public ResponseEntity<Page<Consumer>> getAllConsumers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "100") Integer itemsPerPage) {
        log.info("obtendo todos clientes");

        Page<Consumer> response = service.getAllConsumers(page, itemsPerPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @ResponseBody
    @PostMapping
    public ResponseEntity<Consumer> createConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(service.save(consumer), HttpStatus.CREATED);
    }

    // Atualizar cliente, lembrando que não deve ser possível alterar o saldo do cartão

    @ResponseBody
    @PatchMapping
    public ResponseEntity<Consumer> updateConsumer(@RequestBody Consumer consumer) {
        return new ResponseEntity<>(service.update(consumer), HttpStatus.OK);
    }

    /*
     * Credito de valor no cartão
     *
     * cardNumber: número do cartão
     * value: valor a ser creditado (adicionado ao saldo)
     */
    @ResponseBody
    @PatchMapping(value = "card/balance")
    public ResponseEntity<Consumer> addBalance(@RequestBody @Valid  BalanceModel balance) {

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
    @ResponseBody
    @PostMapping(value = "/buy")
    public ResponseEntity<Consumer> buy(@RequestBody BuyModel buyModel) {

        return new ResponseEntity<>(service.buy(buyModel), HttpStatus.OK);
    }

}
