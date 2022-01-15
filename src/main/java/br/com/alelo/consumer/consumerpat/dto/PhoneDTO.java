package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Phone;
import lombok.Data;

import java.io.Serializable;

@Data
public class PhoneDTO implements Serializable {

    private int mobile;
    private int residence;
    private int phoneNumber;

    public PhoneDTO() {
    }

    public PhoneDTO(Phone phone) {
        this.mobile = phone.getMobilePhoneNumber();
        this.residence = phone.getResidencePhoneNumber();
        this.phoneNumber = phone.getPhoneNumber();
    }
}
