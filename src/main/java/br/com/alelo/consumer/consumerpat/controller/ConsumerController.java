package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository consumerRepository;

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers() {
        return consumerRepository.getAllConsumersList();
    }

    /* Cadastrar novos clientes */
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
        consumerRepository.save(consumer);
    }

    // Não deve ser possível alterar o saldo do cartão
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.PUT)
    public void updateConsumer(@RequestBody Consumer consumer) {
        consumerRepository.update(consumer.getId(), consumer.getName(), consumer.getDocumentNumber(), consumer.getBirthDate(), consumer.getMobilePhoneNumber(), consumer.getResidencePhoneNumber(), consumer.getPhoneNumber(), consumer.getEmail(), consumer.getStreet(), consumer.getNumber(), consumer.getCity(), consumer.getCountry(), consumer.getPortalCode(), consumer.getFoodCardNumber(), consumer.getFuelCardNumber(), consumer.getDrugstoreNumber());
    }

    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/setCardBalance", method = RequestMethod.PUT)
    public void setBalance(int cardNumber, double value) {
        Consumer consumer = null;
        consumer = consumerRepository.findByDrugstoreNumber(cardNumber);

        if(consumer != null) {
            // é cartão de farmácia
            consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + value);
            consumerRepository.save(consumer);
        } else {
            consumer = consumerRepository.findByFoodCardNumber(cardNumber);
            if(consumer != null) {
                // é cartão de refeição
                consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
                consumerRepository.save(consumer);
            } else {
                // É cartão de combustivel
                consumer = consumerRepository.findByFuelCardNumber(cardNumber);
                consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
                consumerRepository.save(consumer);
            }
        }
    }
}
