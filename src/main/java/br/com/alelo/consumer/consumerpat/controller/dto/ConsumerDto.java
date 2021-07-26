package br.com.alelo.consumer.consumerpat.controller.dto;

import java.time.LocalDate;
import java.util.Collection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class ConsumerDto {

    Long      id;
    String    name;
    String    documentNumber;
    LocalDate birthDate;

    Collection<AddressDto> addresses;
    Collection<ContactDto> contacts;
    Collection<CardDto>    cards;

}
