package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.dataprovider.entity.CardEntity;
import br.com.alelo.consumerpat.core.dataprovider.entity.ExtractEntity;
import br.com.alelo.consumerpat.core.domain.CardDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.CardBuyV1RequestDto;

import java.time.LocalDateTime;

public class ExtractEntityMapper {

    public static ExtractEntity convert(CardBuyV1RequestDto requestDto, CardEntity cardEntity, CardDomain cardDomain) {
        if (requestDto == null || cardEntity == null || cardDomain == null) {
            return null;
        }

        return ExtractEntity.builder()
                .card(cardEntity)
                .dateBuy(LocalDateTime.now())
                .establishmentName(requestDto.getEstablishmentName())
                .establishmentType(requestDto.getEstablishmentType())
                .productDescription(requestDto.getProductDescription())
                .value(cardDomain.getValueBuy())
                .build();
    }
}
