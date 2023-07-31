package br.com.alelo.consumer.consumerpat.infrastructure.repository.payment.entity;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.EstablishmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EstablishmentEntity {

    @Column(name = "establishment_name")
    private String establishmentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "establishment_type")
    private EstablishmentType establishmentType;
}
