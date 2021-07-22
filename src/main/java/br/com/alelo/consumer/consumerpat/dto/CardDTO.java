package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardDTO {

    private Integer id;
    private Integer cardNumber;
    private Double cardBalance;
    private Type type;
    private String cardDescription;

}
