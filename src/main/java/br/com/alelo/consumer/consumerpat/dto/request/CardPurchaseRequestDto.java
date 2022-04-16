package br.com.alelo.consumer.consumerpat.dto.request;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CardPurchaseRequestDto {
    Integer establishmentType;
    String establishmentName;
    Integer cardNumber;
    String productDescription;
    Double value;
}
