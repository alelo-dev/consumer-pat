package br.com.alelo.consumer.consumerpat.facade;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.controller.dto.ContactDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ContactSaveDto;
import br.com.alelo.consumer.consumerpat.facade.converter.ContactConverter;
import br.com.alelo.consumer.consumerpat.service.ContactService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactFacade {
    
    private final ContactService service;
    
    public void save(final ContactSaveDto contact) {
        var dto = ContactDto.builder().value(contact.getValue()).type(contact.getType())
                .consumerId(contact.getConsumerId()).build();
        service.save(ContactConverter.toEntity(dto));
    }
    
    public void update(final ContactDto contact) {
        service.update(ContactConverter.toEntity(contact));
    }
    
}
