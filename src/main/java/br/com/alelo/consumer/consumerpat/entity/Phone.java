package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.dto.PhoneDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;

    public Phone() {
    }

    public Phone(PhoneDTO phone) {

        this.mobilePhoneNumber = phone.getMobile();
        this.residencePhoneNumber = phone.getResidence();
        this.phoneNumber = phone.getPhoneNumber();
    }
}
