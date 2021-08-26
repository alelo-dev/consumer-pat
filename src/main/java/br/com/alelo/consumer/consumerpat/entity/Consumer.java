package br.com.alelo.consumer.consumerpat.entity;



import javax.persistence.*;
import java.util.*;


@Entity
public class Consumer {

    protected Consumer() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    int documentNumber;
    Date birthDate;

    @OneToMany(mappedBy = "consumer", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "consumer", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "consumer", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    public Consumer(String name, int documentNumber, Date birthDate) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
    }

    public boolean addAddress(Address address){
        address.setConsumer(this);
        return this.addresses.add(address);
    }

    public boolean addContact(Contact contact){
        contact.setConsumer(this);
        return this.contacts.add(contact);
    }

    public boolean addCard(Card card){
        card.setConsumer(this);
        return this.cards.add(card);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
