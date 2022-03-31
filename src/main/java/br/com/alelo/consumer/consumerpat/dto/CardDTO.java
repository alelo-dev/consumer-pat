package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    @NonNull
    private Long number;

    @NonNull
    private Double balance;

    @NonNull
    private TypeDTO type;
}
