package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers()
    {
        return this.consumerService.getAllConsumersList();
    }


    /* Cadastrar novos clientes */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public Consumer createConsumer(@RequestBody Consumer consumer) {
        return this.consumerService.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody Consumer consumer) {

        this.consumerService.updateConsumer(consumer);

    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance/{drugstoreNumber}/{value}", method = RequestMethod.GET)
    public void setBalance(@PathVariable int drugstoreNumber,@PathVariable double value) {
        this.consumerService.setBalance(drugstoreNumber, value);
    }

    /* O valores só podem ser debitados dos cartões com os tipos correspondentes ao tipo do estabelecimento da compra.
     *  Exemplo: Se a compra é em um estabelecimeto de Alimentação(food) então o valor só pode ser debitado do cartão e alimentação
     *
     * Tipos de estabelcimentos
     * 1 - Alimentação (food)
     * 2 - Farmácia (DrugStore)
     * 3 - Posto de combustivel (Fuel)
     */

    @ResponseBody
    @RequestMapping(value = "/buy/{establishmentType}/{establishmentName}/{cardNumber}/{productDescription}/{value}", method = RequestMethod.GET)
    public void buy(@PathVariable int establishmentType,
                    @PathVariable String establishmentName,
                    @PathVariable int cardNumber,
                    @PathVariable String productDescription,
                    @PathVariable double value) {
        this.consumerService.buy(establishmentType,establishmentName,cardNumber,productDescription,value);

    }

}
