package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.exception.ValidationCard;
import br.com.alelo.consumer.consumerpat.exception.ValidationConsumer;
import br.com.alelo.consumer.consumerpat.exception.ValidationEstablishment;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.types.EstablishmentTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    private final ConsumerService consumerService;//[Controller deve acessar uma SERVICE e não acessar a Base Diretamente]


    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.getListAllConsumers();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    @ResponseBody //[Sergio] retornar o status http correto
    public ResponseEntity createConsumer(@RequestBody Consumer consumer) {

        try {
            consumerService.createConsumer(consumer);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso!");
        } catch (ValidationConsumer e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // Não deve ser possível alterar o saldo do cartão
    //[Sergio] Mais apropriado utilizar o metodo PATCH por se tratar de um update parcial
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity updateConsumer(@RequestBody Consumer consumer) {
        try {
            consumerService.updateConsumer(consumer);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso!");
        } catch (ValidationConsumer e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    @ResponseBody
    //[Sergio] Retornar o status http correto
    public ResponseEntity setBalance(Long cardNumber, double value) {
        try {
            consumerService.setBalance(cardNumber, value);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso!");
        } catch (ValidationCard e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public ResponseEntity buy(int establishmentType, String establishmentName, Long cardNumber, String productDescription, double value) {
        try {
            consumerService.buy(establishmentType,establishmentName,cardNumber,productDescription,value);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso!");
        } catch (ValidationEstablishment e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ValidationCard e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
