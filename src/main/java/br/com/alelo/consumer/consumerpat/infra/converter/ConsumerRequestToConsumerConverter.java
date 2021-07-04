package br.com.alelo.consumer.consumerpat.infra.converter;

import br.com.alelo.consumer.consumerpat.controller.request.AddressRequest;
import br.com.alelo.consumer.consumerpat.controller.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.controller.request.ContactRequest;
import br.com.alelo.consumer.consumerpat.domain.consumer.Address;
import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.domain.consumer.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerRequestToConsumerConverter implements Converter<ConsumerRequest, Consumer>{
    @Override
    public Consumer convert(ConsumerRequest domain) {
        final AddressRequest domainAddress = domain.getAddress();
        final ContactRequest domainContact = domain.getContact();

        return Consumer.builder()
                .name(domain.getName())
                .documentNumber(domain.getDocumentNumber())
                .birthDate(domain.getBirthDate())
                .address(Address
                        .builder()
                        .city(domainAddress.getCity())
                        .country(domainAddress.getCountry())
                        .number(domainAddress.getNumber())
                        .portalCode(domainAddress.getPortalCode())
                        .street(domainAddress.getStreet()).build())
                .contact(Contact
                        .builder()
                        .email(domainContact.getEmail())
                        .phoneNumber(domainContact.getPhoneNumber())
                        .build())
//                .cards(domain
//                        .getCards()
//                        .stream()
//                        .map(toCard::convert)
//                        .collect(Collectors.toList()))
                .build();
    }
}
