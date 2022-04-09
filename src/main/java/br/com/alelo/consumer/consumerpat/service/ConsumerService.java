package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.orm.ConsumerORM;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<? extends Consumer> all() {
        return allConsumers.findAll();
    }

    public Consumer persist(ConsumerDTO newConsumer) {
        var address= addressService.persist(newConsumer.getAddress());
        var contacts = contactsService.persist(newConsumer.getContacts());
        var fuelCard = cardService.persist(newConsumer.getFuelCard());
        var drugstoreCard = cardService.persist(newConsumer.getDrugstoreCard());
        var foodCard = cardService.persist(newConsumer.getFoodCard());

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

    public Optional<Consumer> merge(Integer consumerId, ConsumerDTO latest) {
        var found = allConsumers.findById(consumerId);
        if (found.isEmpty()) {
            return Optional.empty();
        }

        var consumer = found.get();
        var address = addressService.merge(consumer.getAddress(), latest.getAddress());
        var contacts = contactsService.merge(consumer.getContacts(), latest.getContacts());
        var fuelCard = cardService.merge(consumer.getFuelCard(), latest.getFuelCard());
        var drugstoreCard = cardService.merge(consumer.getDrugstoreCard(), latest.getDrugstoreCard());
        var foodCard = cardService.merge(consumer.getFoodCard(), latest.getFoodCard());

        consumer.setAddress(address);
        consumer.setContacts(contacts);
        consumer.setFuelCard(fuelCard);
        consumer.setDrugstoreCard(drugstoreCard);
        consumer.setFoodCard(foodCard);
        consumer.setBirthDate(latest.getBirthDate());
        consumer.setDocumentNumber(latest.getDocumentNumber());
        return Optional.of(consumer);
    }

    public Optional<ConsumerORM> withId(int id) {
        return allConsumers.findById(id);
    }
}
