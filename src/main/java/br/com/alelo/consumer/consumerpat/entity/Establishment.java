package br.com.alelo.consumer.consumerpat.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Establishment extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstablishmentTypeEnum establishmentType;

    @NotNull
    String CNPJ;

    @NotNull
    String name;

}
