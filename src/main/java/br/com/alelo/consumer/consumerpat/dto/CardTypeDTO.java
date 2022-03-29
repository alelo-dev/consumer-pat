package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardTypeDTO {
    Integer id;

    String name;
}
