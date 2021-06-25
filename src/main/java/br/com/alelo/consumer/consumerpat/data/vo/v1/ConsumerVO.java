package br.com.alelo.consumer.consumerpat.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "id", "name", "documentNumber", "birthDate", "mobilePhoneNumber", "residencePhoneNumber",
        "phoneNumber", "email", "streetAddress", "numberAddress", "cityAddress", "countryAddress", "postalCodeAddress",
        "foodCardNumber", "foodCardBalance", "fuelCardNumber", "fuelCardBalance", "drugstoreCardNumber",
        "drugstoreCardBalance" })
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
    @JsonProperty("streetAddress")
    String street;
    @JsonProperty("numberAddress")
    Integer number;
    @JsonProperty("cityAddress")
    String city;
    @JsonProperty("countryAddress")
    String country;
    @JsonProperty("postalCodeAddress")
    String postalCode;

    //cards
    Long foodCardNumber;
    BigDecimal foodCardBalance;

    Long fuelCardNumber;
    BigDecimal fuelCardBalance;

    Long drugstoreCardNumber;
    BigDecimal drugstoreCardBalance;
}
