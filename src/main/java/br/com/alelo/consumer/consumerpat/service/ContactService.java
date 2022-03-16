package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Contact;
import br.com.alelo.consumer.consumerpat.respository.ContactRepository;

@Service
public class ContactService {
 
    private final ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> findAllByConsumer(String consumerName) {
        return this.repository.findByConsumerName(consumerName);
    }

    public Contact createContact(Contact entity) {
        return this.repository.save(entity);
    }

    public Contact updateContact(Contact contact, Integer addressId) {
        final var updatedAddress = this.repository.findById(addressId)
            .map(entity -> {
                entity.setType(contact.getType());
                entity.setPhoneNumber(entity.getPhoneNumber());
                entity.setEmailAddress(entity.getEmailAddress());
                return this.repository.save(entity);
            }).orElseThrow();

        return updatedAddress;
    }
}
