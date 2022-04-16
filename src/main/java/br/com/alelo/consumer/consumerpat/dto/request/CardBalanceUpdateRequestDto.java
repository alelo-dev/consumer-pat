package br.com.alelo.consumer.consumerpat.dto.request;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CardBalanceUpdateRequestDto {
    Integer cardNumber;
    Double value;
}
