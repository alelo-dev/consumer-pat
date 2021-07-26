package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.controller.dto.AddressDto;
import br.com.alelo.consumer.consumerpat.controller.dto.AddressSaveDto;
import br.com.alelo.consumer.consumerpat.facade.AddressFacade;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressFacade facade;

    /**
     * Criar um endereço
     *
     * @param address
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress(@RequestBody final AddressSaveDto address) {
        facade.save(address);
    }
    
    /**
     * Atualizar um contato
     *
     * @param address
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAddress(@RequestBody final AddressDto address) {
        facade.update(address);
    }

}
