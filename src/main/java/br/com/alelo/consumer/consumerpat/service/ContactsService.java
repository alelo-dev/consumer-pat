package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerContactsDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerContactsORM;
import br.com.alelo.consumer.consumerpat.respository.ConsumerContactsRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

    private final ConsumerContactsRepository allConsumersContacts;

    public ContactsService(ConsumerContactsRepository allConsumersContacts) {
        this.allConsumersContacts = allConsumersContacts;
    }

    public ConsumerContactsORM save(ConsumerContactsDTO newConsumerContacts) {
        if (newConsumerContacts == null) {
            return null;
        }
        var contacts = new ConsumerContactsORM();
        contacts.setEmail(newConsumerContacts.getEmail());
        contacts.setPhoneNumber(newConsumerContacts.getPhoneNumber());
        contacts.setMobilePhoneNumber(newConsumerContacts.getMobilePhoneNumber());
        contacts.setResidencePhoneNumber(newConsumerContacts.getResidencePhoneNumber());
        return allConsumersContacts.save(contacts);
    }
}
