package br.com.alelo.consumer.consumerpat.controller.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class CardDto {
    
    Long       id;
    Long       consumerId;
    String     number;
    String     type;
    BigDecimal balance;
    
}
