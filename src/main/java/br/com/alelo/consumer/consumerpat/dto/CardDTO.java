package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CardDTO {
    @NonNull
    private Long number;

    @NonNull
    private Double balance;

    @NonNull
    private TypeDTO type;
}
