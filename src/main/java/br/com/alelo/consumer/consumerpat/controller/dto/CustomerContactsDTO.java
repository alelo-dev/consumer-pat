package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.CustomerContact;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerContactsDTO {

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    public static CustomerContactsDTO from(CustomerContact contacts) {
        var dto = new CustomerContactsDTO();
        dto.mobilePhoneNumber = contacts.getMobilePhoneNumber();
        dto.residencePhoneNumber = contacts.getResidencePhoneNumber();
        dto.phoneNumber = contacts.getPhoneNumber();
        dto.email = contacts.getEmail();
        return dto;
    }

}
