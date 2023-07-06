package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CardRepository cardRepository;

    /* Listar todos os clientes (obs.: tabela possui cerca de 50.000 registros) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<Consumer> listAllConsumers(@RequestParam(defaultValue= "0", required = false)
                                               Integer page ,
                                           @RequestParam(defaultValue= "5", required = false)
                                               Integer pageSize) {
        log.info("obtendo todos clientes");
        Pageable paging = PageRequest.of(page, pageSize);

        try {
            var consumers = consumerRepository.getAllConsumersList(paging);

            return consumers;
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ArrayList<Consumer>();
    }

    /**
     * Cadastrar novos clientes
     * @param consumer
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createConsumer(@RequestBody Consumer consumer) {
            consumerRepository.save(consumer);
    }

    /**
     * Atualizar cliente
     * @param consumer
     */
    @RequestMapping(value = "/update/{consumerId}", method = RequestMethod.PUT)
    public void updateConsumer(@PathVariable(value = "consumerId") int consumerId, @RequestBody Consumer consumer) {

        var consumerFromDb = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumerFromDb.isPresent()){
            consumer.setId(consumerFromDb.get().getId());
            consumerRepository.save(consumer);
        }

    }

    /**
     * Cadastra endereço do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/create/{consumerId}/address", method = RequestMethod.POST)
    public void createAddress(@PathVariable(value = "consumerId") int consumerId, @RequestBody Address address) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumer.isPresent()){
            address.setConsumer(consumer.get());
            addressRepository.save(address);
        }
    }

    /**
     * Atualizar endereco do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/update/{consumerId}/address", method = RequestMethod.PUT)
    public void updateAddress(@PathVariable(value = "consumerId") int consumerId,@RequestBody Address address) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumer.isPresent()){
            address.setConsumer(consumer.get());
            addressRepository.save(address);
        }
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/create/{consumerId}/contact", method = RequestMethod.POST)
    public void createContact(@PathVariable(value = "consumerId") int consumerId, @RequestBody Contact contact) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumer.isPresent()) {
            contact.setConsumer(consumer.get());
            contactRepository.save(contact);
        }
    }

    /**
     * Atualiza contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/update/{consumerId}/contact", method = RequestMethod.PUT)
    public void updateContact(@PathVariable(value = "consumerId") int consumerId,@RequestBody Contact contact) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumer.isPresent()) {
            contact.setConsumer(consumer.get());
            contactRepository.save(contact);
        }
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param card
     */
    @RequestMapping(value = "/create/{consumerId}/card", method = RequestMethod.POST)
    public void createCard(@PathVariable(value = "consumerId") int consumerId, @RequestBody Card card) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumer.isPresent()) {
            card.setConsumer(consumer.get());
            cardRepository.save(card);
        }
    }
}
