package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private int documentNumber;
    private Date birthDate;

    //contacts
    private int mobilePhoneNumber;
    private int residencePhoneNumber;
    private int phoneNumber;
    private String email;

    //Address
    private String street;
    private int number;
    private String city;
    private String country;
    private int portalCode;

    //cards
    private Long foodCardNumber;
    private Long fuelCardNumber;
    private Long drugstoreNumber;

    public ConsumerDTO(Consumer consumer) {
        id = consumer.getId();
        name = consumer.getName();
        documentNumber = consumer.getDocumentNumber();
        birthDate = consumer.getBirthDate();

        mobilePhoneNumber = consumer.getMobilePhoneNumber();
        residencePhoneNumber = consumer.getResidencePhoneNumber();
        phoneNumber = consumer.getPhoneNumber();
        email = consumer.getEmail();

        street = consumer.getStreet();
        number = consumer.getNumber();
        city = consumer.getCity();
        country = consumer.getCountry();
        portalCode = consumer.getPortalCode();
        foodCardNumber = consumer.getFoodCardNumber();
        fuelCardNumber = consumer.getFuelCardNumber();
        drugstoreNumber = consumer.getDrugstoreNumber();
    }
}
