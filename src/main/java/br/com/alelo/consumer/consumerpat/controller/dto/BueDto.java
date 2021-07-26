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
public class BueDto {
    
    Long       consumerId;
    String     cardNumber;
    Integer    establishmentType;
    String     establishmentName;
    String     productDescription;
    BigDecimal value;
    
}
