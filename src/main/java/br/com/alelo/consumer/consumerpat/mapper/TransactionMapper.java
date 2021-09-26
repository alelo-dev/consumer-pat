package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Establishment;
import br.com.alelo.consumer.consumerpat.domain.Transaction;
import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.dto.EstablishmentDTO;
import br.com.alelo.consumer.consumerpat.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    @Autowired
    EstablishmentMapper establishmentMapper;

    @Autowired
    CardMapper cardMapper;

    public TransactionDTO toDTO(Transaction entity) {
        return TransactionDTO.builder()
                .value(Optional.of(entity.getValue()).orElse(0.0))
                .productDescription(Optional.ofNullable(entity.getProductDescription()).orElse(null))
                .dateBuy(Optional.ofNullable(entity.getDateBuy()).orElse(LocalDateTime.now()))
                .establishment(Optional.ofNullable(entity.getEstablishment()).isPresent() ? this.establishmentMapper.toDTO(entity.getEstablishment()) :
                                                                                            EstablishmentDTO.builder().build())
                .card(Optional.ofNullable(entity.getCard()).isPresent() ? this.cardMapper.toDTO(entity.getCard()) : CardDTO.builder().build())
                .id(Optional.ofNullable(entity.getId()).orElse(null))
                .build();
    }

    public Transaction toEntity(TransactionDTO dto) {
        return Transaction.builder()
                .value(Optional.of(dto.getValue()).orElse(0.0))
                .productDescription(Optional.ofNullable(dto.getProductDescription()).orElse(null))
                .dateBuy(Optional.ofNullable(dto.getDateBuy()).orElse(LocalDateTime.now()))
                .establishment(Optional.ofNullable(dto.getEstablishment()).isPresent() ? this.establishmentMapper.toEntity(dto.getEstablishment()) :
                        Establishment.builder().build())
                .card(Optional.ofNullable(dto.getCard()).isPresent() ? this.cardMapper.toEntity(dto.getCard()) : Card.builder().build())
                .id(Optional.ofNullable(dto.getId()).orElse(null))
                .build();
    }

    public List<Transaction> toEntityList(List<TransactionDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<TransactionDTO> toDTOList(List<Transaction> entityses) {
        return entityses.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
