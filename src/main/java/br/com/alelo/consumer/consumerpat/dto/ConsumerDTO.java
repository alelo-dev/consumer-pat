package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ConsumerDTO {

    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    //Contact
    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    //Cards
    private Integer foodCardNumber;
    private Double foodCardBalance;
    private Integer fuelCardNumber;
    private Double fuelCardBalance;
    private Integer drugstoreCardNumber;
    private Double drugstoreCardBalance;

    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int postalCode;

    public EstablishmentType getType() {
        if(foodCardNumber != null) {
            return EstablishmentType.FOOD;
        } else if(fuelCardNumber != null) {
            return EstablishmentType.FUEL;
        }
        return EstablishmentType.DRUGSTORE;
    }
}
