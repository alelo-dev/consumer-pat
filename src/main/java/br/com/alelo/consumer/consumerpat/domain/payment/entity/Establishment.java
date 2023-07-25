package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
public class Establishment {

    @NotBlank(message = "Establishment name is required")
    private String establishmentName;
    @NotNull(message = "Establishment type is required")
    private EstablishmentType establishmentType;

    public Establishment(String establishmentName, EstablishmentType establishmentType) {
        this.establishmentName = establishmentName;
        this.establishmentType = establishmentType;
    }
}
