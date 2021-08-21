package br.com.alelo.consumer.consumerpat.mapper.response;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.dto.v1.response.ConsumerV1ResponseDto;

public class ContactV1ResponseMapper {

    public static ConsumerV1ResponseDto convert(ConsumerEntity consumerEntity) {
        return ConsumerV1ResponseDto.builder()
                .name(consumerEntity.getName())
                .birthDate(consumerEntity.getBirthDate())
                .document(consumerEntity.getDocument())
                .build();
    }
}
