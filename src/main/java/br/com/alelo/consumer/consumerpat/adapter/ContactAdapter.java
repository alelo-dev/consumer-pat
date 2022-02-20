package br.com.alelo.consumer.consumerpat.adapter;

import br.com.alelo.consumer.consumerpat.entity.consumer.Contact;
import br.com.alelo.consumer.consumerpat.vo.ContactVo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactAdapter {

    public static ContactVo modelToVo(Contact contact){

        return ContactVo.builder().id(contact.getId())
                .email(contact.getEmail())
                .mobilePhoneNumber(contact.getMobilePhoneNumber())
                .phoneNumber(contact.getPhoneNumber())
                .residencePhoneNumber(contact.getResidencePhoneNumber())
                .build();
    }

    public static List<ContactVo> modelToVo(List<Contact> contacts){
        if (CollectionUtils.isEmpty(contacts)) {
            return new ArrayList<>();
        }
        return contacts.stream().map(ContactAdapter::modelToVo).collect(Collectors.toList());
    }

    public static Contact voToModel(ContactVo contact){

        return Contact.builder().id(contact.getId())
                .email(contact.getEmail())
                .mobilePhoneNumber(contact.getMobilePhoneNumber())
                .phoneNumber(contact.getPhoneNumber())
                .residencePhoneNumber(contact.getResidencePhoneNumber())
                .build();
    }

    public static List<Contact> voToModel(List<ContactVo> contacts){
        if (CollectionUtils.isEmpty(contacts)) {
            return new ArrayList<>();
        }
        return contacts.stream().map(ContactAdapter::voToModel).collect(Collectors.toList());
    }

}
