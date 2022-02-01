package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.Service.BenefitCardService;
import br.com.alelo.consumer.consumerpat.Service.ConsumerService;
import br.com.alelo.consumer.consumerpat.entity.BenefitCard;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.BenefitCardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
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
    ConsumerRepository repository;

    @Autowired
    BenefitCardService benefitCardService;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerService.listConsumerService();
    }


    /* Cadastrar novos clientes */
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerService.createConsumerService(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public void updateConsumer(@RequestBody Consumer consumer) {
        // Todo: DTO
        repository.save(consumer);
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.POST)
    public void setBalance(int cardNumber, double value) {
        benefitCardService.setBalance(cardNumber, value); //*todo: logica para consultar e adicionar
    }

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
        //*todo: Integrar lógica já feita
    }
}
