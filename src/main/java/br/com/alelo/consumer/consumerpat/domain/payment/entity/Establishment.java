package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Establishment {

    @NotBlank(message = "establishmentName is required")
    private String establishmentName;
    @NotNull(message = "establishmentType is required")
    private EstablishmentType establishmentType;

    public Establishment(String establishmentName, EstablishmentType establishmentType) {
        this.establishmentName = establishmentName;
        this.establishmentType = establishmentType;
    }
}
