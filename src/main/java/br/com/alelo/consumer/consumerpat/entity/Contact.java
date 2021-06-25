package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class Contact {

	private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;
}
