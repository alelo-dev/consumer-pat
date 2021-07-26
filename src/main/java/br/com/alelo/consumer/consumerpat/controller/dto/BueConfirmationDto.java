package br.com.alelo.consumer.consumerpat.controller.dto;

import java.math.BigDecimal;
import java.time.Instant;

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
public class BueConfirmationDto {

    String confirmationCode;

    String     cardNumber;
    String     establishmentType;
    String     establishmentName;
    String     productDescription;
    Instant    date;
    BigDecimal value;

}
