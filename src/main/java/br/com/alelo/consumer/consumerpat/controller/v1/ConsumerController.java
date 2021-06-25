package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.data.vo.v1.ConsumerVO;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/consumer/v1")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    ExtractRepository extractRepository;


    /* Deve listar todos os clientes (cerca de 500) */
    @ApiOperation("Retorna lista de todos os Clientes")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/consumerList")
    public ResponseEntity<?> listAllConsumers(Pageable pageable) {
        return new ResponseEntity<>(consumerService.getAllConsumersList(pageable), HttpStatus.OK);
    }


    /* Cadastrar novos clientes */
    @ApiOperation("Cadastra um novo Cliente")
    @PostMapping("/createConsumer")
    public ResponseEntity<?> createConsumer(@RequestBody ConsumerVO consumer) {
        return new ResponseEntity<>(consumerService.save(consumer), HttpStatus.CREATED);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ApiOperation("Altera um Consumidor")
    @PostMapping("/updateConsumer")
    public ResponseEntity<?> updateConsumer(@RequestBody ConsumerVO consumer) {

        return new ResponseEntity<>(consumerService.save(consumer), HttpStatus.OK);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ApiOperation("Altera do Saldo de um Cartão")
    @PostMapping("/setcardbalance")
    public ResponseEntity<Object> setBalance(@RequestParam("cardNumber") Long cardNumber,
                                             @RequestParam("amount") BigDecimal value) {
        consumerService.incrementBalance(cardNumber, value);
        return ResponseEntity.ok().build();
    }

}
