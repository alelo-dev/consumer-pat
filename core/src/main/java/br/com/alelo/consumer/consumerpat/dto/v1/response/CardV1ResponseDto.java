package br.com.alelo.consumer.consumerpat.dto.v1.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CardV1ResponseDto {

    private String foodCard;
    private Double foodCardBalance;
    private String fuelCard;
    private Double fuelCardBalance;
    private String drugstoreCard;
    private Double drugstoreCardBalance;
}
