package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public ResponseEntity<Object> createConsumer(@RequestBody Consumer consumer) {
        try{
            return new ResponseEntity<Object>(consumerService.saveConsumer(consumer), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>("Erro ao salvar os dados do consumidor", HttpStatus.BAD_REQUEST);
        }
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public ResponseEntity<Object> updateConsumer(@RequestBody Consumer consumer) {
        try{
            return new ResponseEntity<Object>(consumerService.saveConsumer(consumer), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>("Erro ao atualizar os dados do consumidor", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
	public ResponseEntity<Object> setBalance(@RequestParam int cardNumber, @RequestParam double value) {
		try {
			return new ResponseEntity<Object>(consumerService.setBalance(cardNumber, value), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Erro ao salvar os dados do cartão", HttpStatus.BAD_REQUEST);
		}
	}

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResponseEntity<Object> buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        try {
			return new ResponseEntity<Object>(consumerService.buy(establishmentType, establishmentName, cardNumber, productDescription, value), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Erro ao realizar a compra", HttpStatus.BAD_REQUEST);
		}
    }
}
