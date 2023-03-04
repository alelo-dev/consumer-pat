package br.com.alelo.consumer.consumerpat.infrastructure.repositories.wrappers;

import br.com.alelo.consumer.consumerpat.domain.entities.Address;
import br.com.alelo.consumer.consumerpat.domain.entities.Card;
import br.com.alelo.consumer.consumerpat.domain.entities.Consumer;
import br.com.alelo.consumer.consumerpat.domain.entities.Contact;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.AddressEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.CardEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repositories.entities.ContactEntity;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ConsumerEntityWrapper {

    private ConsumerEntity consumerEntity;

    public ConsumerEntityWrapper(ConsumerEntity consumerEntity) {
        this.consumerEntity = consumerEntity;
    }

    public ConsumerEntity copyFromProperties(Consumer consumer) {
        BeanUtils.copyProperties(consumer, consumerEntity, "id");

        copyFromPropertyContact(consumer.getContact());
        copyFromPropertyAddress(consumer.getAddress());
        copyFromPropertyCards(consumer.getCards());

        return consumerEntity;
    }

    private void copyFromPropertyContact(Contact contact) {
        if (contact == null) {
            return;
        }

        if (consumerEntity.getContact() == null) {
            consumerEntity.setContact(new ContactEntity());
        }
        BeanUtils.copyProperties(contact, consumerEntity.getContact(), "id", "consumer");
        consumerEntity.getContact().setConsumer(consumerEntity);
    }

    private void copyFromPropertyAddress(Address address) {
        if (address == null) {
            return;
        }

        if (consumerEntity.getAddress() == null) {
            consumerEntity.setAddress(new AddressEntity());
        }
        BeanUtils.copyProperties(address, consumerEntity.getAddress(), "id", "consumer");
        consumerEntity.getAddress().setConsumer(consumerEntity);
    }

    private void copyFromPropertyCards(List<Card> cards) {
        if (cards == null) {
            return;
        }

        if (consumerEntity.getCards() == null) {
            consumerEntity.setCards(new ArrayList<>());
        }
        for (Card card : cards) {
            if (card != null) {
                val cardEntity = new CardEntity();
                BeanUtils.copyProperties(card, cardEntity, "id", "consumer");
                cardEntity.setEstablishmentType(card.getEstablishmentType().getValue());
                cardEntity.setConsumer(consumerEntity);

                consumerEntity.getCards().add(cardEntity);
            }
        }
    }
}
