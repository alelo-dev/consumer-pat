package br.com.alelo.consumer.consumerpat.controller.v1;

import br.com.alelo.consumer.consumerpat.dtos.v1.Consumer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    /**
     * Sem tempo para fazer o versionamento funcionar...
     * @return
     */

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return Arrays.asList(new Consumer());
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
       
    }


    // /*
    //  * Deve creditar(adicionar) um valor(value) em um no cartão.
    //  * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
    //  * para isso deve usar o número do cartão(cardNumber) fornecido.
    //  */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public void setBalance(int cardNumber, double value) {
        
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        
    }

}