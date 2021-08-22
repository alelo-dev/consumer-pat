package br.com.alelo.consumerpat.core.mapper.domain;

import br.com.alelo.consumerpat.core.domain.ConsumerDomain;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerCreateV1RequestDto;
import br.com.alelo.consumerpat.core.dto.v1.request.ConsumerUpdateV1RequestDto;

public class ConsumerDomainMapper {

    public static ConsumerDomain convert(ConsumerCreateV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return ConsumerDomain.builder()
                .address(AddressDomainMapper.convert(request.getAddress()))
                .cards(CardDomainMapper.convert(request.getCards()))
                .contact(ContactDomainMapper.convert(request.getContact()))
                .birthDate(request.getBirthDate())
                .document(request.getDocument())
                .name(request.getName())
                .build();
    }

    public static ConsumerDomain convert(ConsumerUpdateV1RequestDto request) {
        if (request == null) {
            return null;
        }

        return ConsumerDomain.builder()
                .address(AddressDomainMapper.convert(request.getAddress()))
                .contact(ContactDomainMapper.convert(request.getContact()))
                .birthDate(request.getBirthDate())
                .name(request.getName())
                .build();
    }
}
