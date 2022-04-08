package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerContactsRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ExtractRepository extractRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ConsumerContactsRepository consumerContactsRepository;

    @Autowired
    ConsumerService consumerService;

    /* Deve listar todos os clientes (cerca de 500) */
    //TODO return DTO
    //TODO fix path
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/consumerList", method = RequestMethod.GET)
    public List<ConsumerDTO> listAllConsumers() {
        return repository.findAll().stream()
                .map(ConsumerDTO::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ConsumerDTO> getById(@PathVariable int id) {
        var customerFound = repository.findById(id);
        if (customerFound.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var found = customerFound.get();
        return ResponseEntity.ok(ConsumerDTO.from(found));
    }

    //TODO POST /customers/
    //TODO move logict to CustomerService
    @RequestMapping(value = "/createConsumer", method = RequestMethod.POST)
    public <T> ResponseEntity<T> createConsumer(@RequestBody ConsumerDTO consumer) {
        var created = consumerService.save(consumer);
        var createdCustomerLink = String.format("/customer/%s", created.getId());
        return ResponseEntity.created(URI.create(createdCustomerLink)).build();
    }

    //TODO move to a serve
    private void saveCascade(ConsumerORM consumer) {
        Optional.ofNullable(consumer.getFoodCard()).ifPresent(foodCard -> consumer.setFoodCard(cardRepository.save(foodCard)));
        Optional.ofNullable(consumer.getFuelCard()).ifPresent(fuelCard -> consumer.setFuelCard(cardRepository.save(fuelCard)));
        Optional.ofNullable(consumer.getAddress()).ifPresent(address -> consumer.setAddress(addressRepository.save(address)));
        Optional.ofNullable(consumer.getContacts()).ifPresent(contacts -> consumer.setContacts(consumerContactsRepository.save(contacts)));
        Optional.ofNullable(consumer.getDrugstoreCard()).ifPresent(drugstoreCard -> consumer.setDrugstoreCard(cardRepository.save(drugstoreCard)));
    }

    // Não deve ser possível alterar o saldo do cartão
    //TODO PUT /customers/{customerId}
    //TODO dto
    //TODO Move logic to CustomerService
    //TODO Not update the card balance
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public <T> ResponseEntity<T> updateConsumer(@RequestBody ConsumerORM consumer) {
        var found = repository.findById(consumer.getId());
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        saveCascade(consumer);
        repository.save(consumer);
        return ResponseEntity.ok().build();
    }


    /*
     * Deve creditar(adicionar) um valor(value) em um no cartão.
     * Para isso ele precisa indenficar qual o cartão correto a ser recarregado,
     * para isso deve usar o número do cartão(cardNumber) fornecido.
     */
    //TODO negative values
    //TODO dto
    //TODO POST /cords/{cardnumber}/recharge
    //TODO extract all logic to CardService
    //TODO refactoring the logic
    @RequestMapping(value = "/setcardbalance", method = RequestMethod.GET)
    public <T> ResponseEntity<T> setBalance(String cardNumber, double value) {
        var found = cardRepository.findById(cardNumber);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var card = found.get();
        card.recharge(value);
        cardRepository.save(card);
        return ResponseEntity.ok().build();
    }

    //TODO POST /orders
    //TODO move all logic inside OrderService
    //TODO create enum for establishmentType
    //TODO check for input negative values
    //TODO check for negative balance
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public <T> ResponseEntity<T> buy(String establishmentName, String cardNumber, String productDescription, double value) {
        var found = cardRepository.findById(cardNumber);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var card = found.get();
        card.minus(value);
        cardRepository.save(card);

        Extract extract = new Extract(establishmentName, productDescription, new Date(), cardNumber, value);
        extractRepository.save(extract);
        return ResponseEntity.ok().build();
    }

}
