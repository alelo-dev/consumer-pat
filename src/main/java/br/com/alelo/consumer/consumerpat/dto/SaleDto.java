package br.com.alelo.consumer.consumerpat.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleDto {

    String cardNumber;
    String productDescription;
    double value;

}
