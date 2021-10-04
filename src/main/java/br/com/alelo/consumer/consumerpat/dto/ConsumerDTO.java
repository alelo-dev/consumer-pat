package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString(of = "name")
public class ConsumerDTO {

    private String name;

    private String documentNumber;

    private Date birthDate;

    private int mobilePhoneNumber;

    private int residencePhoneNumber;

    private int phoneNumber;

    private String email;

    private String street;

    private int number;

    private String city;

    private String country;

    private int portalCode;

    private List<CardDTO> cards;
}
