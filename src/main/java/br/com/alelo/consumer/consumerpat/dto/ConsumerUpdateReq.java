package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ConsumerUpdateReq {

    private String idConsumer;
    @NotNull
    @NotEmpty(message = "Name is not empty")
    private String name;
    @NotNull
    private String documentNumber;
    private Date birthDate;
    private Set<ContactRequest> contacts;
    private Set<AddressRequest> addresses;
    private Set<CardRequest> cards;

    private Set<Contact> contactsSet = new HashSet<>();

    public Consumer updateConsumer(Consumer consumer){
        return consumer.builder()
                .idConsumer(consumer.getIdConsumer())
                .name(this.name)
                .documentNumber(this.documentNumber)
                .birthDate(this.birthDate)
                .contacts(updateContact(consumer.getContacts()))
                .addresses(updateAddress(consumer.getAddresses()))
                .cards(updateCard(consumer.getCards()))
                .build();
    }

    private Set<Contact> updateContact(Set<Contact> contacts) {
        return contacts.stream()
                .map(c-> {
                        return verifyDiffContact(c);
                    }).collect(Collectors.toSet());
    }

    private Contact verifyDiffContact(Contact contact){
        Optional<ContactRequest> contactOptional = this.contacts.stream()
                .filter(c -> c.getIdContact().equals(contact.getIdContact()))
                .findAny();
        if(contactOptional.isPresent()){
            ContactRequest cr = contactOptional.get();
            if(!contact.getEmail().equals(cr.getEmail())) { contact.setEmail(cr.getEmail()); }
            if(!contact.getPhoneNumber().equals(cr.getPhoneNumber())) { contact.setPhoneNumber(cr.getPhoneNumber());}
            if(!contact.getResidencePhoneNumber().equals(cr.getResidencePhoneNumber())) { contact.setResidencePhoneNumber(cr.getResidencePhoneNumber());}
            if(!contact.getMobilePhoneNumber().equals(cr.getMobilePhoneNumber())) {contact.setMobilePhoneNumber(cr.getMobilePhoneNumber());}
        }
        return contact;
    }

    private Set<Address> updateAddress(Set<Address> address) {
       return address.stream()
               .map( a -> {
                   return verifyDiffAddress(a);
               }).collect(Collectors.toSet());
    }

    private Address verifyDiffAddress(Address address) {
        Optional<AddressRequest> addressOptional = this.addresses.stream()
                .filter(c -> c.getIdAdress().equals(address.getIdAdress()))
                .findAny();

        if(addressOptional.isPresent()){
            AddressRequest ar = addressOptional.get();
            if(!address.getCity().equals(ar.getCity())) { address.setCity(ar.getCity()); }
            if(!address.getCountry().equals(ar.getCountry())) { address.setCountry(ar.getCountry()); }
            if(!address.getNumber().equals(ar.getNumber())) { address.setNumber(ar.getNumber()); }
            if(!address.getStreet().equals(ar.getStreet())) { address.setStreet(ar.getStreet()); }
            if(!address.getPostalCode().equals(ar.getPostalCode())) { address.setPostalCode(ar.getPostalCode()); }
        }
        return address;
    }

    private Set<Card> updateCard(Set<Card> cards) {
      return cards.stream()
               .map(c->{
                   return verifyDiffCard(c);
               }).collect(Collectors.toSet());
    }

    private Card verifyDiffCard(Card card){
        Optional<CardRequest> cardOptional = this.cards.stream()
                .filter(c -> c.getIdCard().equals(card.getIdCard()))
                .findAny();
        if(cardOptional.isPresent()){
            CardRequest cr = cardOptional.get();
            if(!card.getCardNumber().equals(cr.getCardNumber())) { card.setCardNumber(cr.getCardNumber());}
        }
        return card;
    }
}
