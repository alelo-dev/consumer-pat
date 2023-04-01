package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;
}
