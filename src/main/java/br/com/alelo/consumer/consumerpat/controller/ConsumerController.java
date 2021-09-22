package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.ResultadoDTO;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.vo.Buy;
import br.com.alelo.consumer.consumerpat.vo.Cards;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumerList")
    public ResponseEntity<List<Consumer>> listAllConsumers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "name") String sortBy) {
        List<Consumer> list = consumerService.listAllConsumers(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Consumer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    public ResponseEntity<ResultadoDTO> createConsumer(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(consumerService.createConsumer(consumer));
    }

    // Não deve ser possível alterar o saldo do cartão
    @PostMapping("/updateConsumer")
    public ResponseEntity<ResultadoDTO> updateConsumer(@RequestBody Consumer consumer)  {
        return ResponseEntity.ok(consumerService.updateConsumer(consumer));
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @PostMapping("/setcardbalance")
    public ResponseEntity<ResultadoDTO> setBalance(@RequestBody Cards cards)  {
        return ResponseEntity.ok(consumerService.setBalance(cards));
    }

    @PostMapping("/buy")
    public ResponseEntity<ResultadoDTO> buy(@RequestBody Buy buy) {
        return ResponseEntity.ok(consumerService.buy(buy));
    }
}
