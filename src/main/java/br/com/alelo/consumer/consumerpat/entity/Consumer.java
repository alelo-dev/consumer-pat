package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.alelo.consumer.consumerpat.helper.RequestConsumerUpdate;

import java.time.LocalDateTime;
import java.util.List;



@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String documentNumber;

    @Email
    private String email;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
    private LocalDateTime birthDate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Card> cards;

    public Consumer(RequestConsumerUpdate requestConsumerUpdate) {
        this.name = requestConsumerUpdate.getName();
        this.documentNumber = requestConsumerUpdate.getDocumentNumber();
        this.email = requestConsumerUpdate.getEmail();
        this.birthDate = requestConsumerUpdate.getBirthDate();
        this.phones = requestConsumerUpdate.getPhones();
        this.address = requestConsumerUpdate.getAddress();
    }
}
