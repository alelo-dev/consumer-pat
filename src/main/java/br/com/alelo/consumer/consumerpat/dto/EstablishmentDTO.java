package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstablishmentDTO {
    private Integer id;

    private String name;

    private TypeDTO type;
}
