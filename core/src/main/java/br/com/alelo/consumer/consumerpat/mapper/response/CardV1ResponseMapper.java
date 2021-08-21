package br.com.alelo.consumer.consumerpat.mapper.response;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.dto.v1.response.CardV1ResponseDto;

public class CardV1ResponseMapper {

    public static CardV1ResponseDto convert(CardEntity cardEntity) {
        if(cardEntity == null) {
            return null;
        }

        return CardV1ResponseDto.builder()
                .drugstoreCard(cardEntity.getDrugstoreCard())
                .drugstoreCardBalance(cardEntity.getDrugstoreCardBalance())
                .foodCard(cardEntity.getFoodCard())
                .foodCardBalance(cardEntity.getFoodCardBalance())
                .fuelCard(cardEntity.getFuelCard())
                .fuelCardBalance(cardEntity.getFuelCardBalance())
                .build();
    }
}
