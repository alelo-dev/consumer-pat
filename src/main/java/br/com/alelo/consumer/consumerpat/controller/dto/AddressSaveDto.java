package br.com.alelo.consumer.consumerpat.controller.dto;

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
public class AddressSaveDto {

    Long    consumerId;
    String  street;
    Integer number;
    String  city;
    String  country;
    String  postalCode;

}
