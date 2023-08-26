package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.enums.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO para representar informações de um consumidor.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDTO {

    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;

    private Integer mobilePhoneNumber;
    private Integer residencePhoneNumber;
    private Integer phoneNumber;
    private String email;

    // Cartões
    private Integer foodCardNumber;
    private Double foodCardBalance;
    private Integer fuelCardNumber;
    private Double fuelCardBalance;
    private Integer drugstoreCardNumber;
    private Double drugstoreCardBalance;

    // Endereço
    private String street;
    private int number;
    private String city;
    private String country;
    private int postalCode;

    /**
     * Obtém o tipo de estabelecimento com base nos cartões associados.
     *
     * @return O tipo de estabelecimento.
     */
    public EstablishmentType getType() {
        if (foodCardNumber != null) {
            return EstablishmentType.FOOD;
        } else if (fuelCardNumber != null) {
            return EstablishmentType.FUEL;
        }
        return EstablishmentType.DRUGSTORE;
    }
}
