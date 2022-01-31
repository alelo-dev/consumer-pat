package br.com.alelo.consumer.consumerpat.domain.dto;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateResponseDTO extends BaseDTO{
    private static final long serialVersionUID = 1L;

    private Integer cardNumber;
    private CardType cardType;
}
