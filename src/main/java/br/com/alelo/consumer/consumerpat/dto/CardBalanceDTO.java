package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CardBalanceDTO {

    private Integer cardNumber;
    private Double cardBalance;
    private Integer cardType;

}
