package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.enums.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Cards
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

    public ConsumerDTO(String name  ) {
        this.name = name;
    }

    /**
     * Obtém o tipo de estabelecimento com base nos cartões associados.
     *
     * @return O tipo de estabelecimento.
     */
    public CompanyType getType() {
        if (foodCardNumber != null) {
            return CompanyType.FOOD;
        } else if (fuelCardNumber != null) {
            return CompanyType.FUEL;
        }
        return CompanyType.DRUGSTORE;
    }
}
