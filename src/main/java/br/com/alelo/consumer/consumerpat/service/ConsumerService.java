package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contacts;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.alelo.consumer.consumerpat.utils.PropertiesUtils.getNullPropertyNames;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    private Consumer findConsumerById(Long consumerId) {
        return consumerRepository.findById(consumerId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Consumer")));
    }

    public Iterable<Consumer> getAllConsumersPaginate(Pageable pageable) {
        return consumerRepository.getAllConsumersList(pageable);
    }

    public Consumer registerConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public void updateConsumer(Long consumerId, Consumer updtConsumer) {

        Consumer actualConsumer = findConsumerById(consumerId);

        List<Contacts> contactsList = actualConsumer.getContactsList();
        List<Card> cardList = actualConsumer.getCardsList();
        List<Address> addressList = actualConsumer.getAddressList();

        updateConsumerData(updtConsumer, actualConsumer, contactsList, cardList, addressList);

        consumerRepository.save(actualConsumer);
    }

    private void updateConsumerData(Consumer updtConsumer, Consumer actualConsumer, List<Contacts> contactsList, List<Card> cardList, List<Address> addressList) {
        BeanUtils.copyProperties(updtConsumer, actualConsumer, getNullPropertyNames(updtConsumer, "contactsList", "addressList", "cardsList"));

        if (updtConsumer.getContactsList() != null && !updtConsumer.getContactsList().isEmpty()) {
            for (Contacts updtContact : updtConsumer.getContactsList()) {
                updateContactData(contactsList, updtContact);
            }
        }

        if (updtConsumer.getAddressList() != null && !updtConsumer.getAddressList().isEmpty()) {
            for (Address updtAddress : updtConsumer.getAddressList()) {
                updateAddressData(addressList, updtAddress);
            }
        }

        if (updtConsumer.getCardsList() != null && !updtConsumer.getCardsList().isEmpty()) {
            for (Card updtCard : updtConsumer.getCardsList()) {
                updateCardData(cardList, updtCard);
            }
        }
    }

    private void updateContactData(List<Contacts> contactsList, Contacts updtContact) {
        Contacts actualContacts = contactsList.stream().filter(actual -> actual.getId().equals(updtContact.getId())).
                findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Contacts")));

        if (actualContacts != null) {
            BeanUtils.copyProperties(updtContact, actualContacts, getNullPropertyNames(updtContact));
        }
    }

    private void updateAddressData(List<Address> addressList, Address updtAddress) {
        Address actualAddress = addressList.stream().filter(actual -> actual.getId().equals(updtAddress.getId())).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Address")));
        if (actualAddress != null) {
            BeanUtils.copyProperties(updtAddress, actualAddress, getNullPropertyNames(updtAddress));
        }
    }

    private void updateCardData(List<Card> cardList, Card updtCard) {
        Card actualCard = cardList.stream().filter(actual -> actual.getId().equals(updtCard.getId())).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Entidade { %s } não encontrada", "Card")));
        if (actualCard != null) {
            BeanUtils.copyProperties(updtCard, actualCard, getNullPropertyNames(updtCard, "cardBalance"));
        }
    }
}
