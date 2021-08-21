package br.com.alelo.consumer.consumerpat.mapper.response;

import br.com.alelo.consumer.consumerpat.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumer.consumerpat.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumer.consumerpat.mapper.BaseMapper;

public class ConsumerV1ResponseMapper implements BaseMapper<ConsumerEntity, ConsumerV1ResponseDto> {

    @Override
    public ConsumerV1ResponseDto convert(ConsumerEntity consumerEntity) {
        return ConsumerV1ResponseDto.builder()
                .name(consumerEntity.getName())
                .birthDate(consumerEntity.getBirthDate())
                .document(consumerEntity.getDocument())
                .build();
    }
}
