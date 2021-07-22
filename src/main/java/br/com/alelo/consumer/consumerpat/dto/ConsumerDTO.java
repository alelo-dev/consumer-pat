package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.AddressRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ConsumerDTO {

    private String name;
    private String documentNumber;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;
    private List<Address> addresses;
    @OneToMany(mappedBy = "consumer")
    private Set<Card> cards;

    public ConsumerDTO(String name, String documentNumber, Date birthDate, String mobilePhoneNumber, String residencePhoneNumber, String phoneNumber, String email, List<Address> addresses, Set<Card> cards) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.residencePhoneNumber = residencePhoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addresses = addresses;
        this.cards = cards;
    }

    public Consumer toModel(EntityManager manager, AddressRepository addressRepository) {
        return new Consumer(name, documentNumber, birthDate, mobilePhoneNumber, residencePhoneNumber, phoneNumber, email, addresses, cards);
    }
}
