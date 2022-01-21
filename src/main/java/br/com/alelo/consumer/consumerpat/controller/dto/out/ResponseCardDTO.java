package br.com.alelo.consumer.consumerpat.controller.dto.out;

import br.com.alelo.consumer.consumerpat.enums.CardTypeEnum;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCardDTO {

    private Integer number;
    private Double balance;
    private CardTypeEnum cardType;
}
