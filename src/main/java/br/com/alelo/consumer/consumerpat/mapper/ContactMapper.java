package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.response.ContactDto;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact toEntity(ContactDto contactDto) {
        return Contact.builder()
                .email(contactDto.getEmail())
                .phoneNumber(contactDto.getPhoneNumber())
                .mobilePhoneNumber(contactDto.getMobilePhoneNumber())
                .residencePhoneNumber(contactDto.getResidencePhoneNumber())
                .build();
    }

    public ContactDto toDto(Contact contact) {
        return ContactDto.builder()
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .mobilePhoneNumber(contact.getMobilePhoneNumber())
                .residencePhoneNumber(contact.getResidencePhoneNumber())
                .build();
    }
}
