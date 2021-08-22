package br.com.alelo.consumerpat.core.mapper.entity;

import br.com.alelo.consumerpat.core.dataprovider.entity.ConsumerEntity;
import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.v1.request.ConsumerUpdateV1RequestDto;
import br.com.alelo.consumerpat.core.mapper.domain.ConsumerDomainMapper;

public class ConsumerEntityMapper {

    public static ConsumerEntity convert(ConsumerDomain domain) {
        if (domain == null) {
            return null;
        }

        return ConsumerEntity.builder()
                .consumerCode(domain.getConsumerCode())
                .address(AddressEntityMapper.convert(domain.getAddress()))
                .cards(CardEntityMapper.convert(domain.getCards()))
                .contact(ContactEntityMapper.convert(domain.getContact()))
                .birthDate(domain.getBirthDate())
                .document(domain.getDocument())
                .name(domain.getName())
                .build();
    }

    public static ConsumerEntity convert(ConsumerUpdateV1RequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }

        ConsumerDomain consumerDomain = ConsumerDomainMapper.convert(requestDto);
        return ConsumerEntityMapper.convert(consumerDomain);
    }
}
