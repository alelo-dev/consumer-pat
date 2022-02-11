package br.com.alelo.consumer.consumerpat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "cards" })
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String documentNumber;
    private Date birthDate;

    // contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    // Address
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postalCode;

    @OneToMany
    @JoinColumn(name = "consumer_id")
    private List<Card> cards = new ArrayList<>();

    public Consumer() {

    }

    public Consumer(Long id) {
        this.id = id;
    }

    public Consumer(ConsumerDTO consumerDTO) {
        this.id = consumerDTO.getId();
        this.address1 = consumerDTO.getAddress1();
        this.address2 = consumerDTO.getAddress2();
        this.birthDate = consumerDTO.getBirthDate();
        if (consumerDTO.getCards() != null) {
            this.cards = consumerDTO.getCards()
                    .stream().map(cardDTO -> new Card(this.id, cardDTO)).collect(Collectors.toList());
        }
    }

}
