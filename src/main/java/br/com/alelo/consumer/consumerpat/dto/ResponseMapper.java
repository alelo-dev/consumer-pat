package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import org.apache.catalina.mapper.Mapper;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ResponseMapper {

    public static ConsumerResponse toConsumer(Consumer consumer){
        ConsumerResponse response = ConsumerResponse
                .builder()
                .id(consumer.getId())
                .name(consumer.getName())
                .documentNumber(consumer.getDocumentNumber())
                .birthDate(consumer.getBirthDate())
                .mobilePhoneNumber(consumer.getMobilePhoneNumber())
                .residencePhoneNumber(consumer.getResidencePhoneNumber())
                .phoneNumber(consumer.getPhoneNumber())
                .email(consumer.getEmail())
                .street(consumer.getStreet())
                .number(consumer.getNumber())
                .city(consumer.getCity())
                .country(consumer.getCountry())
                .postalCode(consumer.getPostalCode())
                .build();

        if(Objects.nonNull(consumer.getCards()) && !consumer.getCards().isEmpty()){
            response.setCards(
                    consumer.getCards().stream().map(c -> ResponseMapper.toCard(c)).collect(Collectors.toSet())
            );
        }

        return response;
    }

    public static CardResponse toCard(Card card){
        Set<ExtractResponse> extracts = card.getExtracts() != null ? card.getExtracts().stream().map(e -> toExtract(e)).collect(Collectors.toSet()) : null;

        return CardResponse
                .builder()
                .cardType(card.getCardType())
                .cardNumber(card.getCardNumber())
                .balance(card.getBalance())
                .consumerDocument(card.getConsumer().getDocumentNumber())
                .extracts(extracts)
                .build();
    }

    public static ExtractResponse toExtract(Extract extract){
        return ExtractResponse.builder()
                .establishmentName(extract.getEstablishmentName())
                .productDescription(extract.getProductDescription())
                .dateBuy(extract.getDateBuy())
                .value(extract.getValue())
                .build();
    }
}
