package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.responses.ApiResponse;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
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

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Cadastrar novos clientes
     * @param consumer
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> createConsumer(@RequestBody @Valid Consumer consumer, BindingResult bindingResult) {

        log.info("Cadastrando um cliente");
        ApiResponse response = new ApiResponse();

        try {
            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao criar usuário");
            }else{
                consumerRepository.save(consumer);
                response.setMessage("Usuário criado com sucesso");
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        response.setData(consumer);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Atualizar cliente
     * @param consumer
     */
    @RequestMapping(value = "/update/{consumerId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> updateConsumer(@PathVariable(value = "consumerId") int consumerId,
                                                      @RequestBody @Valid Consumer consumer,
                                                      BindingResult bindingResult) {
        log.info("Atualizando um cliente");

        ApiResponse response = new ApiResponse();

        try{
            var consumerFromDb = consumerRepository.findById(Integer.valueOf(consumerId));
            if(consumerFromDb.isEmpty()){
                bindingResult.addError(new ObjectError("Consumer","Usuário não existe para atualização"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao atualizar usuário");
                consumer.setId(consumerId);

            }else{
                consumer.setId(consumerFromDb.get().getId());
                consumerRepository.save(consumer);
                response.setMessage("Usuário atualizado com sucesso");
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }

        response.setData(consumer);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Cadastra endereço do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/create/{consumerId}/address", method = RequestMethod.POST)
    public ResponseEntity createAddress(@PathVariable(value = "consumerId") int consumerId,
                                        @RequestBody @Valid Address address,
                                        BindingResult bindingResult) {
        log.info("Cadastrando um endereço");

        ApiResponse response = new ApiResponse();

        try {


            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            var addressFromDb = addressRepository.findByConsumerId(Integer.valueOf(consumerId));

            if(consumer.isEmpty()){
                bindingResult.addError(new ObjectError("Address","O usuário solicitado para o cadastro de endereço não existe"));
            }

            if(!addressFromDb.isEmpty()){
                bindingResult.addError(new ObjectError("Address","O usuário solicitado para o cadastro de endereço já possuí um endereço cadastrado"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao tentar cadastrar endereço");
            }else{
                address.setConsumer(consumer.get());
                addressRepository.save(address);
                response.setMessage("Endereço cadastrado com sucesso!");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            response.setMessage("Erro ao tentar cadastrar endereço");
       }

        response.setData(address);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Atualizar endereco do cliente
     * @param consumerId
     * @param address
     */
    @RequestMapping(value = "/update/{consumerId}/address", method = RequestMethod.PUT)
    public ResponseEntity updateAddress(@PathVariable(value = "consumerId") int consumerId,
                                        @RequestBody @Valid Address address,
                                        BindingResult bindingResult) {
        log.info("Atualizando um endereço");

        ApiResponse response = new ApiResponse();
        try{
            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            var addressFromDb = addressRepository.findByConsumerId(Integer.valueOf(consumerId));

            if(consumer.isEmpty()){
                bindingResult.addError(new ObjectError("Address","O usuário solicitado para o atualiação de endereço não existe"));
            }

            if(addressFromDb.isEmpty()){
                bindingResult.addError(new ObjectError("Address","O usuário solicitado para o atualização de endereço não possuí um endereço cadastrado"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao tentar atualizar endereço");
            }else{
                address.setId(addressFromDb.get().getId());
                address.setConsumer(consumer.get());
                addressRepository.save(address);
                response.setMessage("Endereço atualizado com sucesso!");
            }


        }catch (Exception e){
            log.error(e.getMessage());
            response.setMessage("Erro ao tentar atualizar endereço");
        }

        response.setData(address);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/create/{consumerId}/contact", method = RequestMethod.POST)
    public ResponseEntity createContact(@PathVariable(value = "consumerId") int consumerId,
                                        @RequestBody @Valid Contact contact,
                                        BindingResult bindingResult) {
        log.info("Cadastrando um contato");

        ApiResponse response = new ApiResponse();

        try {
            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            var contactFromDb = contactRepository.findByConsumerId(Integer.valueOf(consumerId));

            if(consumer.isEmpty()){
                bindingResult.addError(new ObjectError("Contact","O usuário solicitado para o cadastro de contato não existe"));
            }

            if(!contactFromDb.isEmpty()){
                bindingResult.addError(new ObjectError("Contact","O usuário solicitado para o cadastro de contato já possuí um contato cadastrado"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao tentar cadastrar contato");
                response.setData(contact);
            }else{
                contact.setConsumer(consumer.get());
                contactRepository.save(contact);
                response.setMessage("Contato cadastrado com sucesso!");
                response.setData(contact);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            response.setMessage("Erro ao tentar cadastrar contato");
        }

        response.setData(contact);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Atualiza contato do cliente
     * @param consumerId
     * @param contact
     */
    @RequestMapping(value = "/update/{consumerId}/contact", method = RequestMethod.PUT)
    public ResponseEntity updateContact(@PathVariable(value = "consumerId") int consumerId,
                                        @RequestBody @Valid Contact contact,
                                        BindingResult bindingResult) {
        log.info("Atualizando um contato");

        ApiResponse response = new ApiResponse();

        try {
            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            var contactFromDb = contactRepository.findByConsumerId(Integer.valueOf(consumerId));

            if(consumer.isEmpty()){
                bindingResult.addError(new ObjectError("Contact","O usuário solicitado para o atualização de contato não existe"));
            }

            if(contactFromDb.isEmpty()){
                bindingResult.addError(new ObjectError("Contact","O usuário solicitado para o atualização de contato não possuí um contato cadastrado"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao tentar atualizar contato");
            }else{
                contact.setId(contactFromDb.get().getId());
                contact.setConsumer(consumer.get());
                contactRepository.save(contact);
                response.setMessage("Contato atualizado com sucesso!");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            response.setMessage("Erro ao tentar atualizar contato");
        }

        response.setData(contact);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * Cadastra contato do cliente
     * @param consumerId
     * @param card
     */
    @RequestMapping(value = "/create/{consumerId}/card", method = RequestMethod.POST)
    public ResponseEntity createCard(@PathVariable(value = "consumerId") int consumerId,
                                     @RequestBody @Valid Card card,
                                     BindingResult bindingResult) {
        log.info("Cadastrando um cartão");

        ApiResponse response = new ApiResponse();

        try{

            var consumer = consumerRepository.findById(Integer.valueOf(consumerId));
            if(consumer.isEmpty()){
                bindingResult.addError(new ObjectError("Card","O usuário solicitado para o cadastro do cartao não existe"));
            }

            if (bindingResult.hasErrors()) {
                response.addErrors(bindingResult);
                response.setMessage("Erro ao cadastrar o cartão");
            }else{
                card.setConsumer(consumer.get());
                cardRepository.save(card);
                response.setMessage("Cartão cadastrado com sucesso!");
            }
        }catch (IllegalArgumentException i){
            log.error(i.getMessage());
        }catch (OptimisticLockingFailureException o ){
            log.error(o.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage());
            response.setMessage("Erro ao cadastrar o cartão");
        }

        response.setData(card);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
