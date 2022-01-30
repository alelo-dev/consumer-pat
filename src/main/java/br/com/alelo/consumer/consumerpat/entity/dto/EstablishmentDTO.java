package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class EstablishmentDTO {

    private String establishmentName;
    private CardType cardTypeAccepted;
    private LocalDateTime createDate;
}
