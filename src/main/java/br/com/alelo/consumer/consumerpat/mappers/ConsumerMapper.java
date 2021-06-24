package br.com.alelo.consumer.consumerpat.mappers;

import br.com.alelo.consumer.consumerpat.domain.model.Consumer;
import br.com.alelo.consumer.consumerpat.dto.ConsumerCreationDto;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDto;

public class ConsumerMapper {

    public static ConsumerResponseDto mapConsumerToConsumerResponseDto(Consumer consumer){
        return ConsumerResponseDto.builder()
                .address(AddressMapper.mapAddressToAddressCreationDto(consumer.getAddress()))
                .contacts(ContactsMapper.mapContactsToContactsResponseDto(consumer.getContacts()))
                .cards(CardsMapper.mapCardsToCardsResponseDto(consumer.getCards()))
                .birthDate(consumer.getBirthDate())
                .id(consumer.getId())
                .documentNumber(consumer.getDocumentNumber())
                .name(consumer.getName())
                .build();
    }

    public static Consumer mapConsumerCreationDtoToConsumer(ConsumerCreationDto consumerCreationDto){

        var consumer = new Consumer();
                consumer.setAddress(AddressMapper.mapAddressCreationDtoToAddress(consumerCreationDto.getAddress()));
                consumer.setContacts(ContactsMapper.mapContactsDtoToContacts(consumerCreationDto.getContacts()));
                consumer.setCards(CardsMapper.mapCardsCreationDtoToCards(consumerCreationDto.getCards()));
                consumer.setBirthDate(consumerCreationDto.getBirthDate());
                consumer.setDocumentNumber(consumerCreationDto.getDocumentNumber());
                consumer.setName(consumerCreationDto.getName());

        return consumer;
    }
}
