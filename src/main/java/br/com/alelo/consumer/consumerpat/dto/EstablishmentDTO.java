package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class EstablishmentDTO {
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private TypeDTO type;
}
