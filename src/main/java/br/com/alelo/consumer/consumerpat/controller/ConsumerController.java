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
import org.springframework.http.ResponseEntity;
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

    /**
     *
     * Listar todos os clientes
     *
     * @param page
     * @param pageSize
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public ResponseEntity<List<Consumer>> listAllConsumers(
            @RequestParam(defaultValue= "0", required = false) Integer page ,
            @RequestParam(defaultValue= "5", required = false) Integer pageSize) {
        log.info("obtendo todos clientes");
        Pageable paging = PageRequest.of(page, pageSize);

        try {
            var consumers = consumerRepository.getAllConsumersList(paging);

            return new ResponseEntity<List<Consumer>>(consumers, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Cadastrar novos clientes
     * @param consumer
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createConsumer(@RequestBody Consumer consumer) {
        try {
            consumerRepository.save(consumer);

            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Atualizar cliente
     * @param consumer
     */
    @RequestMapping(value = "/update/{consumerId}", method = RequestMethod.PUT)
    public ResponseEntity updateConsumer(@PathVariable(value = "consumerId") int consumerId, @RequestBody Consumer consumer) {

        var consumerFromDb = consumerRepository.findById(Integer.valueOf(consumerId));
        if(consumerFromDb.isPresent()){
            consumer.setId(consumerFromDb.get().getId());
            consumerRepository.save(consumer);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Cadastra endereço do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/create/{consumerId}/address", method = RequestMethod.POST)
    public ResponseEntity createAddress(@PathVariable(value = "consumerId") int consumerId, @RequestBody Address address) {
        var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
        var addressFromDb = addressRepository.findByConsumerId(Integer.valueOf(consumerId));
        if(consumer.isPresent() && addressFromDb == null){
            address.setConsumer(consumer.get());
            addressRepository.save(address);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Atualizar endereco do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/update/{consumerId}/address", method = RequestMethod.PUT)
    public ResponseEntity updateAddress(@PathVariable(value = "consumerId") int consumerId, @RequestBody Address address) {
        try{
            var addressFromDb = addressRepository.findByConsumerId(Integer.valueOf(consumerId));
            if(addressFromDb != null){
                var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
                address.setId(addressFromDb.getId());
                address.setConsumer(consumer.get());
                addressRepository.save(address);
                return new ResponseEntity(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/create/{consumerId}/contact", method = RequestMethod.POST)
    public ResponseEntity createContact(@PathVariable(value = "consumerId") int consumerId, @RequestBody Contact contact) {
        try {
            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            var contactFromDb = contactRepository.findByConsumerId(Integer.valueOf(consumerId));
            if(consumer.isPresent() && contactFromDb == null) {
                contact.setConsumer(consumer.get());
                contactRepository.save(contact);
                return new ResponseEntity(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Atualiza contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/update/{consumerId}/contact", method = RequestMethod.PUT)
    public ResponseEntity updateContact(@PathVariable(value = "consumerId") int consumerId,
                                        @RequestBody Contact contact) {
        try {
            var contactFromDb = contactRepository.findByConsumerId(Integer.valueOf(consumerId));

            if(contactFromDb != null) {
                var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
                contact.setId(contactFromDb.getId());
                contact.setConsumer(consumer.get());
                contactRepository.save(contact);
                return new ResponseEntity(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }


        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param card
     */
    @RequestMapping(value = "/create/{consumerId}/card", method = RequestMethod.POST)
    public ResponseEntity createCard(@PathVariable(value = "consumerId") int consumerId, @RequestBody Card card) {
        try{
            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            if(consumer.isPresent()) {
                card.setConsumer(consumer.get());
                cardRepository.save(card);
                return new ResponseEntity(HttpStatus.OK);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
