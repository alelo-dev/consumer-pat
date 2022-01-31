package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDTO {

    private Integer id;
    private String establishmentName;
    private CardType cardTypeAccepted;
    private LocalDateTime createDate;
}
