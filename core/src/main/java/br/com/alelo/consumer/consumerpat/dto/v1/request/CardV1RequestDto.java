package br.com.alelo.consumer.consumerpat.dto.v1.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class CardV1RequestDto {

    private String foodCard;
    private Double foodCardBalance;
    private String fuelCard;
    private Double fuelCardBalance;
    private String drugstoreCard;
    private Double drugstoreCardBalance;
}
