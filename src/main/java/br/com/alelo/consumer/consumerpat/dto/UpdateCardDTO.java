package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateCardDTO {
    private Long id;
    private String number;
    private Set<CardType> types;
}
