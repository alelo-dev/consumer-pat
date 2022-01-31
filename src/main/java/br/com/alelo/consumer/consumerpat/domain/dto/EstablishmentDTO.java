package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
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
