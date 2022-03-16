package br.com.alelo.consumer.consumerpat.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.service.AddressService;


@Controller
@RequestMapping("/addresses")
public class AddressesController {

    private final AddressService service;
    
    @Autowired
    public AddressesController(AddressService service) {
        this.service = service;
    }

    /* Deve listar todos os clientes (cerca de 500) */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Address> listAllAdressByConsumer(@RequestParam(name = "consumerName") String consumerName) {
        return service.findAllByConsumer(consumerName);
    }

    /* Cadastrar novos clientes */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createAddress(@RequestBody Address address) {
        final var newAddress = service.createAddress(address);

        final var location = URI.create("/addresses/".concat(newAddress.getId().toString()));
        return ResponseEntity.created(location).build();
    }

    // Não deve ser possível alterar o saldo do cartão
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable("id") Integer id) {
        final var updatedAddress = service.updateAddress(address, id);

        return ResponseEntity.ok(updatedAddress);
    }

}
