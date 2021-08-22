package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.core.dto.v1.response.ConsumerV1ResponseDto;
import br.com.alelo.consumerpat.core.mapper.response.pagination.PaginatedBaseMapper;

public class ConsumerV1ResponseMapperPaginated implements PaginatedBaseMapper<ConsumerEntity, ConsumerV1ResponseDto> {

    @Override
    public ConsumerV1ResponseDto convert(ConsumerEntity consumerEntity) {
        if (consumerEntity == null) {
            return null;
        }

        return ConsumerV1ResponseDto.builder()
                .consumerCode(consumerEntity.getConsumerCode())
                .name(consumerEntity.getName())
                .birthDate(consumerEntity.getBirthDate())
                .document(consumerEntity.getDocument())
                .address(AddressV1ResponseMapper.convert(consumerEntity.getAddress()))
                .contact(ContactV1ResponseMapper.convert(consumerEntity.getContact()))
                .card(CardV1ResponseMapper.convert(consumerEntity.getCards()))
                .build();
    }
}
