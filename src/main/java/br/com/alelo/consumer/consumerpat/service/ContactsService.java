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

    public ConsumerContactsORM persist(ConsumerContactsDTO newConsumerContacts) {
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

    public ConsumerContactsORM merge(ConsumerContactsORM current, ConsumerContactsDTO latest) {
        if (current == null) {
            return persist(latest);
        }
        current.setEmail(latest.getEmail());
        current.setPhoneNumber(latest.getPhoneNumber());
        current.setMobilePhoneNumber(latest.getMobilePhoneNumber());
        current.setResidencePhoneNumber(latest.getResidencePhoneNumber());
        return allConsumersContacts.save(current);
    }
}
