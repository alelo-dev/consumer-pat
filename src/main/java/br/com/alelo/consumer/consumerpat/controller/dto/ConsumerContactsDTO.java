package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.ConsumerContacts;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsumerContactsDTO {

    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    public static ConsumerContactsDTO from(ConsumerContacts contacts) {
        var dto = new ConsumerContactsDTO();
        dto.mobilePhoneNumber = contacts.getMobilePhoneNumber();
        dto.residencePhoneNumber = contacts.getResidencePhoneNumber();
        dto.phoneNumber = contacts.getPhoneNumber();
        dto.email = contacts.getEmail();
        return dto;
    }

}
