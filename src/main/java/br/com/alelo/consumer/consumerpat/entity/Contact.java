package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 7680993063414343779L;

	@Id
    private String idContact;
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

}
