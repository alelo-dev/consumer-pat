package br.com.alelo.consumer.consumerpat.facade.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerSaveDto;
import br.com.alelo.consumer.consumerpat.controller.dto.ConsumerUpdateDto;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsumerConverter {

    public static ConsumerDto toDto(final Consumer consumer) {
        return ConsumerDto.builder().birthDate(LocalDate.now()).documentNumber(consumer.getDocumentNumber())
                .id(consumer.getId()).name(consumer.getName()).build();
    }

    public static List<ConsumerDto> toDtoList(final List<Consumer> consumers) {

        if (consumers == null) {
            return new ArrayList<>();
        }

        return consumers.stream().map(consumer -> {
            return ConsumerDto.builder().id(consumer.getId()).name(consumer.getName())
                    .documentNumber(consumer.getDocumentNumber()).birthDate(consumer.getBirthDate())
                    .addresses(AddressConverter.toDtoList(consumer.getAddresses()))
                    .cards(CardConverter.toDtoList(consumer.getCards()))
                    .contacts(ContactConverter.toDtoList(consumer.getContacts())).build();
        }).collect(Collectors.toList());

    }

    public static Consumer toEntity(final ConsumerSaveDto consumer) {

        var dto = ConsumerUpdateDto.builder().birthDate(consumer.getBirthDate())
                .documentNumber(consumer.getDocumentNumber()).name(consumer.getName()).build();

        return toEntity(dto);
    }

    public static Consumer toEntity(final ConsumerUpdateDto consumer) {

        if (consumer == null) {
            return null;
        }

        return Consumer.builder().id(consumer.getId()).name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber()).birthDate(consumer.getBirthDate()).build();
    }
}