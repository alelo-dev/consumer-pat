package br.com.alelo.consumer.consumerpat.data.vo.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsumerVO implements Serializable {

    Integer id;
    String name;
    String documentNumber;
    Date birthDate;

    //contacts
    Integer mobilePhoneNumber;
    Integer residencePhoneNumber;
    Integer phoneNumber;
    String email;

    //Address
    String street;
    Integer number;
    String city;
    String country;
    String postalCode;

    //cards
    Integer foodCardNumber;
    BigDecimal foodCardBalance;

    Integer fuelCardNumber;
    BigDecimal fuelCardBalance;

    Integer drugstoreCardNumber;
    BigDecimal drugstoreCardBalance;
}
