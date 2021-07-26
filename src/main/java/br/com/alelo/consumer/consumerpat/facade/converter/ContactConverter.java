package br.com.alelo.consumer.consumerpat.facade.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.controller.dto.ContactDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ContactSaveDto;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Contact;
import br.com.alelo.consumer.consumerpat.model.type.ContactType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactConverter {

    public static Collection<ContactDto> toDtoList(final Collection<Contact> contacts) {

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream().map(contact -> {
            return ContactDto.builder().id(contact.getId()).consumerId(contact.getConsumer().getId())
                    .type(contact.getType().name()).value(contact.getValue()).build();
        }).collect(Collectors.toList());
    }

    public static Collection<ContactDto> toDtoSaveList(final Collection<ContactSaveDto> contacts) {
        return contacts.stream().map(contact -> {
            return ContactDto.builder().value(contact.getValue()).type(contact.getType()).build();
        }).collect(Collectors.toList());
    }

    public static Contact toEntity(final ContactDto contact) {

        return Contact.builder().id(contact.getId()).value(contact.getValue())
                .type(ContactType.valueOf(contact.getType()))
                .consumer(Consumer.builder().id(contact.getConsumerId()).build()).build();
    }

    public static Collection<Contact> toList(final Collection<ContactDto> contacts) {
        if (contacts == null) {
            return null;
        }

        return contacts.stream().map(contact -> {
            return Contact.builder().id(contact.getId()).value(contact.getValue())
                    .type(ContactType.valueOf(contact.getType()))
                    .consumer(Consumer.builder().id(contact.getConsumerId()).build()).build();
        }).collect(Collectors.toList());
    }

}
