package br.com.alelo.consumer.consumerpat.mappers;

import br.com.alelo.consumer.consumerpat.domain.model.Contacts;
import br.com.alelo.consumer.consumerpat.dto.ContactsCreationDto;
import br.com.alelo.consumer.consumerpat.dto.ContactsResponseDto;
import br.com.alelo.consumer.consumerpat.dto.ContactsUpdateDto;

public class ContactsMapper {

    public static ContactsResponseDto mapContactsToContactsResponseDto(Contacts contacts){
        return ContactsResponseDto.builder()
                .id(contacts.getId())
                .email(contacts.getEmail())
                .mobilePhoneNumber(contacts.getMobilePhoneNumber())
                .phoneNumber(contacts.getPhoneNumber())
                .residencePhoneNumber(contacts.getResidencePhoneNumber())
                .build();
    }

    public static Contacts mapContactsDtoToContacts(ContactsCreationDto contactsCreationDto){
        Contacts contacts = new Contacts();
                contacts.setEmail(contactsCreationDto.getEmail());
                contacts.setMobilePhoneNumber(contactsCreationDto.getMobilePhoneNumber());
                contacts.setPhoneNumber(contactsCreationDto.getPhoneNumber());
                contacts.setResidencePhoneNumber(contactsCreationDto.getResidencePhoneNumber());

        return contacts;
    }

    public static Contacts mapContactsUpdateDtoToContacts(ContactsUpdateDto contactsUpdateDto, Contacts contacts){
        contacts.setEmail(contactsUpdateDto.getEmail());
        contacts.setMobilePhoneNumber(contactsUpdateDto.getMobilePhoneNumber());
        contacts.setPhoneNumber(contactsUpdateDto.getPhoneNumber());
        contacts.setResidencePhoneNumber(contactsUpdateDto.getResidencePhoneNumber());

        return contacts;
    }
}
