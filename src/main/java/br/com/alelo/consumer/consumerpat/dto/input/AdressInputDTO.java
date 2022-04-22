package br.com.alelo.consumer.consumerpat.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdressInputDTO {

    @NotNull
    private String street;

    private Integer number;

    @NotNull
    private Integer postalCode;

    @NotNull
    private Long cityId;

    @NotNull
    private Long stateId;

    @NotNull
    private Long countryId;
}
