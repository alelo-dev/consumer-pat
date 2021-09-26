package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Client;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CardMapper {


    @Autowired
    ClientMapper clientMapper;

    public CardDTO toDTO(Card entity) {
        return CardDTO.builder()
                        .cardNumber(entity.getCardNumber())
                       // .id(Optional.ofNullable(entity.getId()).orElse(null))
                        .client(Optional.ofNullable(entity.getClient()).isPresent() ?
                                                    this.clientMapper.toDTO(entity.getClient()) : ClientDTO.builder().build())
                        .type(entity.getType())
                        .balance(entity.getBalance())
                        .build();
    }

    public Card toEntity(CardDTO dto) {
        return Card.builder()
                .cardNumber(dto.getCardNumber())
                //.id(Optional.ofNullable(dto.getId()).orElse(null))
                .client(Optional.ofNullable(dto.getClient()).isPresent() ?
                        this.clientMapper.toEntity(dto.getClient()) : Client.builder().build())
                .type(dto.getType())
                .balance(dto.getBalance())
                .build();
    }

    public List<Card> toEntityList(List<CardDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<CardDTO> toDTOList(List<Card> entityses) {
        return entityses.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
