package br.com.alelo.consumer.consumerpat.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

/*
Pq o dto?
não é recomendado receber/responder no controller o próprio objeto de dominio,
pois deixa acoplado minha camada de apresentação com meu dominio.
 */
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDto {

    @Nullable
    Long id;
    String name;
    String documentNumber;
    LocalDate birthDate;

    //contacts
    String mobilePhoneNumber;
    String residencePhoneNumber;
    String phoneNumber;
    String email;

    //Address
    String street;
    int number;
    String city;
    String country;
    String portalCode;

}
