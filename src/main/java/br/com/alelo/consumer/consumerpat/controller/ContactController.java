package br.com.alelo.consumer.consumerpat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.consumer.consumerpat.controller.dto.ContactDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ContactSaveDto;
import br.com.alelo.consumer.consumerpat.facade.ContactFacade;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    
    private final ContactFacade facade;
    
    /**
     * Criar um contato
     *
     * @param contact
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody final ContactSaveDto contact) {
        facade.save(contact);
    }

    /**
     * Atualizar um contato
     *
     * @param contact
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@RequestBody final ContactDto contact) {
        facade.update(contact);
    }
    
}
