package br.com.alelo.consumer.consumerpat.domain.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsumerDTO extends BaseDTO{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private  Integer documentNumber;
    private  Date birthDate;
    private  String mobilePhoneNumber;
    private String residencePhoneNumber;
    private  String phoneNumber;
    private  String email;
    private  String street;
    private  Integer number;
    private  String city;
    private  String country;
    private  Integer portalCode;


}
