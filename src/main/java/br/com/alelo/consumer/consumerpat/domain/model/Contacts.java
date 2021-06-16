package br.com.alelo.consumer.consumerpat.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;
}
