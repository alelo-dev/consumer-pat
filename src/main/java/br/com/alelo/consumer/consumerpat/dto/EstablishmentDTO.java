package br.com.alelo.consumer.consumerpat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentDTO {
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private TypeDTO type;
}
