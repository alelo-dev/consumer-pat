package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.EstablishmentTypeEnum;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@RequiredArgsConstructor
public class EstablishmentDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private EstablishmentTypeEnum establishmentType;

    private String CNPJ;

    private String name;

}