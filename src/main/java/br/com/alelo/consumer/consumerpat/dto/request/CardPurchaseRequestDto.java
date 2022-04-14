package br.com.alelo.consumer.consumerpat.dto.request;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@Data
@Entity
@EqualsAndHashCode
public class CardPurchaseRequestDto {
    Integer establishmentType;
    String establishmentName;
    Integer cardNumber;
    String productDescription;
    Double value;
}
