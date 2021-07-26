package br.com.alelo.consumer.consumerpat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.model.Contact;
import br.com.alelo.consumer.consumerpat.repository.ContactRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactService {
    
    private final ContactRepository contactRepository;

    @Transactional
    public void save(final Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void update(final Contact contact) {
        var c = contactRepository.findOneByIdAndConsumer(contact.getId(), contact.getConsumer())
                .orElseThrow(() -> new NotFoundException("Contact not found."));
        
        c.merge(contact);
        contactRepository.save(c);
    }

}
