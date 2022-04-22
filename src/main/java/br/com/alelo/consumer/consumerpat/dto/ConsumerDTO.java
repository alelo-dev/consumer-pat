package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Address;
import br.com.alelo.consumer.consumerpat.entity.Contact;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ConsumerDTO {

    private Long id;

    private String name;

    private Integer documentNumber;

    private OffsetDateTime birthDate;

    private List<Contact> contacts;

    private Address address;
}
