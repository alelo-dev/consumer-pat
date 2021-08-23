package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.domain.ExtractDomain;
import br.com.alelo.consumerpat.dataprovider.jpa.entity.ExtractEntity;

import java.time.LocalDateTime;

public class ExtractEntityMapper {

    public static ExtractEntity convert(ExtractDomain domain) {
        if (domain == null) {
            return null;
        }

        return ExtractEntity.builder()
                .id(domain.getId())
                .card(CardEntityMapper.convert(domain.getCard()))
                .dateBuy(LocalDateTime.now())
                .establishmentName(domain.getEstablishmentName())
                .establishmentType(domain.getEstablishmentType())
                .productDescription(domain.getProductDescription())
                .value(domain.getValue())
                .build();
    }
}
