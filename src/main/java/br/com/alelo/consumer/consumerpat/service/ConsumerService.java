package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final ConsumerRepository allConsumers;
    private final CardService cardService;
    private final AddressService addressService;
    private final ContactsService contactsService;

    public ConsumerService(ConsumerRepository allConsumers, CardService cardService, AddressService addressService,
                           ContactsService contactsService) {
        this.allConsumers = allConsumers;
        this.cardService = cardService;
        this.addressService = addressService;
        this.contactsService = contactsService;
    }

    public ConsumerORM save(ConsumerDTO newConsumer) {
        var address= addressService.save(newConsumer.getAddress());
        var contacts = contactsService.save(newConsumer.getContacts());
        var fuelCard = cardService.save(newConsumer.getFuelCard());
        var drugstoreCard = cardService.save(newConsumer.getDrugstoreCard());
        var foodCard = cardService.save(newConsumer.getFoodCard());

        var consumer = new ConsumerORM();
        consumer.setAddress(address);
        consumer.setContacts(contacts);
        consumer.setFuelCard(fuelCard);
        consumer.setDrugstoreCard(drugstoreCard);
        consumer.setFoodCard(foodCard);
        consumer.setBirthDate(newConsumer.getBirthDate());
        consumer.setName(newConsumer.getName());
        consumer.setDocumentNumber(newConsumer.getDocumentNumber());
        return allConsumers.save(consumer);
    }

}
