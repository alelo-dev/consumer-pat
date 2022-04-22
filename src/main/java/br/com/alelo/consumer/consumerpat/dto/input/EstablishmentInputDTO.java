package br.com.alelo.consumer.consumerpat.dto.input;

import br.com.alelo.consumer.consumerpat.entity.EstablishmentTypeEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
public class EstablishmentInputDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstablishmentTypeEnum establishmentType;

    @NotNull
    private String CNPJ;

    @NotNull
    private String name;
}
