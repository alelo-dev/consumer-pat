package br.com.alelo.consumer.consumerpat.controller.dto.in;

import br.com.alelo.consumer.consumerpat.enums.CardTypeEnum;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardDTO {

    private String number;
    private Double balance;
    private CardTypeEnum cardType;
}
