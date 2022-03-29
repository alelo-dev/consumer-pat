package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class ContactDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer mobilePhoneNumber;

    Integer residencePhoneNumber;

    Integer phoneNumber;

    String email;
}
