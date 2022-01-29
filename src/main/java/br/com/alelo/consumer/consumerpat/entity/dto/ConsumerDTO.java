package br.com.alelo.consumer.consumerpat.entity.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsumerDTO {

    private Integer id;
    private String name;
    private  Integer documentNumber;
    private  Date birthDate;

    //contacts
    private  String mobilePhoneNumber;
    private String residencePhoneNumber;
    private  String phoneNumber;
    private  String email;

    //Address
    private  String street;
    private  Integer number;
    private  String city;
    private  String country;
    private  Integer portalCode;


}
