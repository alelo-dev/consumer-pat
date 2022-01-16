package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ConsumerUpdateDTO implements Serializable {

    private String name;
    private String documentNumber;
    private LocalDate birthDate;
    private String email;

    private PhoneDTO phone;

    private AddressDTO address;


}
