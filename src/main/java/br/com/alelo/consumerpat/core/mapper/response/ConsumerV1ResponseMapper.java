package br.com.alelo.consumerpat.core.mapper.response;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.dto.v1.response.ConsumerV1ResponseDto;

public class ConsumerV1ResponseMapper {

    public static ConsumerV1ResponseDto convert(ConsumerDomain consumerDomain) {
        if (consumerDomain == null) {
            return null;
        }

        return ConsumerV1ResponseDto.builder()
                .consumerCode(consumerDomain.getConsumerCode())
                .name(consumerDomain.getName())
                .birthDate(consumerDomain.getBirthDate())
                .document(consumerDomain.getDocument())
                .address(AddressV1ResponseMapper.convert(consumerDomain.getAddress()))
                .contact(ContactV1ResponseMapper.convert(consumerDomain.getContact()))
                .card(CardV1ResponseMapper.convert(consumerDomain.getCards()))
                .build();
    }
}
