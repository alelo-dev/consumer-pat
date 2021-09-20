package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/consumer")
@Api("Api Consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    /* Deve listar todos os clientes (cerca de 500) */
    @GetMapping("/consumerList")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "List consumers", nickname = "listAllConsumers")
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @PostMapping("/createConsumer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createConsumer(@RequestBody @Valid Consumer consumer) {
        consumerService.createConsumer(consumer);
   }

    // Não deve ser possível alterar o saldo do cartão
    @PutMapping("/updateConsumer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsumer(@PathVariable("id") Integer id, @RequestBody @Valid Consumer consumer) {
        consumerService.updateConsumer(id, consumer);
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @GetMapping("/setcardbalance")
    @ResponseStatus(HttpStatus.OK)
    public void setBalance(@RequestBody CardDTO cardDTO) {
        consumerService.setBalance(cardDTO);
    }

    @GetMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public void buy(@RequestBody BuyDTO buyDTO) {
        consumerService.buy(buyDTO);
    }

}
