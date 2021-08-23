package br.com.alelo.consumer.consumerpat.core.mapper.domain;

import br.com.alelo.consumer.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumer.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;
import br.com.alelo.consumer.consumerpat.dataprovider.jpa.entity.ExtractEntity;
import br.com.alelo.consumer.consumerpat.core.domain.ExtractDomain;

import java.time.LocalDateTime;

public class ExtractDomainMapper {

    public static ExtractDomain convert(CardBuyV1RequestDto requestDto, CardDomain cardDomain) {
        if (requestDto == null || cardDomain == null) {
            return null;
        }

        return ExtractDomain.builder()
                .card(cardDomain)
                .dateBuy(LocalDateTime.now())
                .establishmentName(requestDto.getEstablishmentName())
                .establishmentType(requestDto.getEstablishmentType())
                .productDescription(requestDto.getProductDescription())
                .value(cardDomain.getValueForExtract())
                .build();
    }

    public static ExtractDomain convert(ExtractEntity entity) {
        if (entity == null) {
            return null;
        }

        return ExtractDomain.builder()
                .card(CardDomainMapper.convert(entity.getCard()))
                .dateBuy(LocalDateTime.now())
                .establishmentName(entity.getEstablishmentName())
                .establishmentType(entity.getEstablishmentType())
                .productDescription(entity.getProductDescription())
                .value(entity.getValue())
                .build();
    }
}
