package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class CardDTO {

    private Long id;

    private CardTypeEnum cardType;

    private Integer cardNumber;

    private BigDecimal balance;

    private ConsumerResumeDTO consumer;

}